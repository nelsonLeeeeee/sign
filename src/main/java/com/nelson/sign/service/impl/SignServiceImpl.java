package com.nelson.sign.service.impl;

import com.nelson.sign.Repository.CourseTimeRepository;
import com.nelson.sign.Repository.SignRepository;
import com.nelson.sign.config.SignConfig;
import com.nelson.sign.entity.CourseTime;
import com.nelson.sign.entity.Sign;
import com.nelson.sign.service.CourseService;
import com.nelson.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SignServiceImpl  implements SignService{

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTimeRepository courseTimeRepository;

    @Override
    public Sign sign(Sign sign) {
        return this.signRepository.saveAndFlush(sign);
    }

    /**
     * 检查签到状态
     * @param id
     * @param signTime
     * @return
     */
    @Override
    public long checkSignStatus(Long id,Long signTime) {
        CourseTime courseTime =  this.courseTimeRepository.getOne(id);
        if(courseTime==null){
            throw new IllegalArgumentException("ERR_COURSE_NOT_FOUND");
        }
        long result = courseTime.getStartTime() - signTime;
        return result;
    }


    /**
     * 根据学生和课程获取签到状态
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public Map<String, Integer> getLateNumberByStudentAndCourse(Long studentId, Long courseId) {
        int lateNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId, SignConfig.STATUS_LATE);
        int absentNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_ABSENT);
        int replaceNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_REPLACE);
        int leaveNumber = this.signRepository.getCountByStudentIdAndCourseIdAndStatus(studentId,courseId,SignConfig.STATUS_LEAVE);
        Map<String,Integer> resultMap = new HashMap<String,Integer>();
        resultMap.put(SignConfig.LATE,SignConfig.STATUS_LATE);
        resultMap.put(SignConfig.ABSENT,SignConfig.STATUS_ABSENT);
        resultMap.put(SignConfig.REPLACE,SignConfig.STATUS_REPLACE);
        resultMap.put(SignConfig.LEAVE,SignConfig.STATUS_LEAVE);
        return resultMap;
    }

    @Override
    public Sign getSignByTeacherAndClazz(Long teacherId, Long clazzId) {
        return null;
//        return this.signRepository.getSignByTeacherIdAndClazzId(teacherId, clazzId);
    }

    @Override
    public Page<Sign> getStudentSignStatisticsByCourse(Long courseTimeId, String keyword, Integer pageIndex, Integer pageSize) {

        /**
         * 这里不能写下面这两行。。不然会报错的。。。jpa还是没有那么智能
         */
//        CourseTime courseTime = this.courseTimeRepository.getOne(courseTimeId);
//        System.out.println(courseTime.getStartTime());
        Sort sort = new Sort(Sort.Direction.DESC,"student");
        Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);

        return signRepository.findAll(new Specification<Sign>(){
            @Override
            public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("student").get("name").as(String.class), keyword);
                Predicate p2 = criteriaBuilder.equal(root.get("student").get("studentId").as(Long.class), keyword);
//                Predicate p3 = criteriaBuilder.equal(root.get("courseTime").get("id").as(String.class), courseTimeId);
                query.where(criteriaBuilder.or(p1,p2));
//                query.where(criteriaBuilder.and(criteriaBuilder.or(p1,p2),p3));
                return query.getRestriction();
            }
        },pageable);

    }
//           @Override
//           public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//               List<Predicate> list = new ArrayList<Predicate>();
////                if(StringUtil.isNotBlank(keyword)){
////                    Path<Integer> status = root.get("status");
//////                    Path<String> studentId = root.get("student").get("studentId");
////                    Predicate p1 = criteriaBuilder.equal(status, Integer.valueOf(keyword));
//////                    Predicate p2 = criteriaBuilder.like(studentId,"%"+keyword+"%");
////                    list.add(p1);
//////                    list.add(p2);
////                }
////                Predicate pCourse = criteriaBuilder.equal(root.get("courseTime").get("courseTimeId"),courseTimeId);
////                list.add(pCourse);
//               Predicate[] predicates = new Predicate[list.size()];
//               return criteriaBuilder.and(list.toArray(predicates));
//           }
//       },pageable);
//        Page<Sign>  page = this.signRepository.findAll();
//        return null;
//    }

//    public Page<Sign> findBookCriteria(Integer pageIndex, Integer pageSize,  String keyword) {
//        Sort sort = new Sort(Sort.Direction.DESC,"student");
//        Pageable pageable = PageRequest.of(pageIndex,pageSize,sort);
//        return signRepository.findAll(new Specification<Sign>(){
//            @Override
//            public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate p1 = criteriaBuilder.equal(root.get("student").get("name").as(String.class), keyword);
//                Predicate p2 = criteriaBuilder.equal(root.get("student").get("studentId").as(Long.class), keyword);
////                Predicate p3 = criteriaBuilder.equal(root.get("author").as(String.class), keyword);
//                query.where(criteriaBuilder.or(p1,p2));
//                return query.getRestriction();
//            }
//        },pageable);
//    }




    /**
     * ERR
     * //        Specification<Sign> specification =

     Page<Sign> page = this.signRepository.findAll(new Specification<Sign>() {
    @Override
    public Predicate toPredicate(Root<Sign> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> list = new ArrayList<Predicate>();
    if(null!=keyword&&!"".equals(keyword)){
    //                   list.add(criteriaBuilder.like(root.get("student").get("name").as(String.class), keyword));
    list.add(criteriaBuilder.like(root.get("student").get("studentId").as(String.class), keyword));
    }
    //               if(null!=bookQuery.getIsbn()&&!"".equals(bookQuery.getIsbn())){
    //                   list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getIsbn()));
    //               }
    //               if(null!=bookQuery.getAuthor()&&!"".equals(bookQuery.getAuthor())){
    //                   list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getAuthor()));
    //               }
    Predicate[] p = new Predicate[list.size()];
    return criteriaBuilder.and(list.toArray(p));
    }
    },pageable);
     return page;
     *
     *
     */


}
