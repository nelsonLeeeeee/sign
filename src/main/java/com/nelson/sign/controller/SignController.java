package com.nelson.sign.controller;

import com.nelson.sign.entity.Course;
import com.nelson.sign.entity.Sign;
import com.nelson.sign.enums.ResultEnum;
import com.nelson.sign.handle.SignException;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.service.SignService;
import com.nelson.sign.utils.Result;
import com.nelson.sign.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CourseService courseService;

    @RequestMapping("/sign")
    public Result<Sign> sign(@Valid Sign sign, HttpServletRequest request){
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

    public Result<Integer> getLateNumberByCourse(@RequestParam(name = "courseId") Long courseId){
       int number = this.signService.getLateNumberByCourse(courseId);
       return ResultUtil.success(number);
    }

    @RequestMapping("/getSignByStudent")
    public Result<Sign> getSignByStudent(@RequestParam(name = "teacherId") Long teacherId,
                                   @RequestParam(name = "clazzId") Long clazzId){
        Sign sign = this.signService.getSignByTeacherAndClazz(teacherId,clazzId);
       return ResultUtil.success(sign);
    }


}
