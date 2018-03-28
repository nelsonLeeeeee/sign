package com.nelson.sign.controller;

import com.nelson.sign.Repository.SignRepository;
import com.nelson.sign.entity.Course;
import com.nelson.sign.entity.Sign;
import com.nelson.sign.enums.ResultEnum;
import com.nelson.sign.handle.SignException;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.service.SignService;
import com.nelson.sign.utils.Result;
import com.nelson.sign.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sign/api")
public class SignController {
    @Autowired
    private SignService signService;

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private CourseService courseService;

    /**
     * 学生签到
     * @param sign
     * @param request
     * @return
     */
    @RequestMapping("/sign")
    public Result<Sign> sign(@Valid Sign sign, HttpServletRequest request){

        //判断有没有签到
       if(this.signRepository.getSignByCourseTimeAndStudent(sign.getCourseTime(),sign.getStudent())!=null){
           throw  new SignException(ResultEnum.ERR_HAS_SIGNED);
       }

        //获取客户端IP
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            System.out.println("X-Real-IP ip: " + ip);
        }
        sign.setIp(ip);
        //签到时间
        Long signTime =System.currentTimeMillis();
        sign.setSignTime(signTime);
        //通过courseId获取任课教师
       Course course = sign.getCourseTime().getCourse();
        if(course!=null){
            sign.setTeacher(course.getTeacher());
        }
        //判断该课程是否迟到 -：表示迟到 +：表示正常 1：表示正常， 0：表示迟到
        long lateTime  = this.signService.checkSignStatus(sign.getCourseTime().getId(),signTime);
        if(lateTime>0){
            sign.setStatus(1);
        }else{
            sign.setStatus(0);
        }
        lateTime = Math.abs(lateTime)/(1000*60);
        if(sign.getStatus()==1 && lateTime>30){
            throw new SignException(ResultEnum.ERR_SIGN_EARLY);
        }else if (sign.getStatus()==0 && lateTime>30){
            throw new SignException(ResultEnum.ERR_SIGN_LATE);
        }

        sign = this.signService.sign(sign);
        return ResultUtil.success(sign);
    }

    /**
     * 该学生在该课程下的到课情况（针对于学生）
     * @param studentId
     * @param courseId
     * @return
     */
    @RequestMapping("/getStudentSignStatistics")
    public Result<Map<String,Integer>> getStudentSignStatistics(@RequestParam(name = "studentId") Long studentId,
                                            @RequestParam(name="courseId") Long courseId){
        Map<String,Integer> resultMap = this.signService.getLateNumberByStudentAndCourse(studentId,courseId);
        return ResultUtil.success(resultMap);
    }


    /**
     * 该课程下所有学生在到课情况列表
     * 支持查询 ： 学生姓名  学号
     * @param courseTimeId
     * @param keyword
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/getStudentSignStatisticsByCourse")
    public Result<Page<Sign>> getStudentSignStatisticsByCourse(@RequestParam(name = "courseTimeId") Long courseTimeId,String keyword,Integer pageIndex,Integer pageSize){
//       Page<Sign> pages = this.signService.findBookCriteria(pageIndex,pageSize,keyword);
        Page<Sign> pages = this.signService.getStudentSignStatisticsByCourse(courseTimeId,keyword,pageIndex,pageSize);
       return ResultUtil.success(pages);
    }

    @RequestMapping("/getSignByStudent")
    public Result<Sign> getSignByStudent(@RequestParam(name = "teacherId") Long teacherId,
                                   @RequestParam(name = "clazzId") Long clazzId){
        Sign sign = this.signService.getSignByTeacherAndClazz(teacherId,clazzId);
       return ResultUtil.success(sign);
    }


}
