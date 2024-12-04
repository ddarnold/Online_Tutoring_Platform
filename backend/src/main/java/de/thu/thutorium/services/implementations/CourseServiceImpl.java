package de.thu.thutorium.services.implementations;

import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.database.repositories.CourseRepository;
import de.thu.thutorium.api.transferObjects.CourseDTO;
import de.thu.thutorium.api.frontendMappers.CourseMapper;
import de.thu.thutorium.services.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/** Service class responsible for handling operations related to courses. */
@Service
public class CourseServiceImpl implements CourseService {

  @Autowired private CourseRepository courseRepository;

  @Autowired private CourseMapper courseMapper;

  @Override
  public CourseDTO findCourseById(Long id) {
    CourseDBO course = courseRepository.findCourseById(id);
    return courseMapper.toDTO(course);
  }

  @Override
  public List<CourseDTO> findCoursesByTutorName(String firstName, String lastName) {
    List<CourseDBO> courses = courseRepository.findByTutorFirstNameAndLastName(firstName, lastName);
    return courseMapper.toDTOList(courses);
  }

  @Override
  public List<CourseDTO> findCoursesByFullTutorName(String tutorName) {
    List<CourseDBO> courses = courseRepository.findByTutorFullName(tutorName);
    return courseMapper.toDTOList(courses);
  }

  @Override
  public List<CourseDTO> findCoursesByName(String name) {
    List<CourseDBO> courses = courseRepository.findCourseByName(name);
    return courseMapper.toDTOList(courses);
  }

  @Override
  public List<CourseDTO> getCoursesByCategory(String categoryName) {
    List<CourseDBO> courses = courseRepository.findCoursesByCategoryName(categoryName);
    return courses.stream().map(courseMapper::toDTO).toList();
  }

  @Override
  public Long getTotalCountOfCourses() {
    return courseRepository.countAllCourses();
  }
}
