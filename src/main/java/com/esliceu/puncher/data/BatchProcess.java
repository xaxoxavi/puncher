package com.esliceu.puncher.data;

import com.esliceu.puncher.data.model.*;
import com.esliceu.puncher.data.model.Student;
import com.esliceu.puncher.data.model.Subject;
import com.esliceu.puncher.data.repository.*;
import com.esliceu.puncher.parser.model.*;
import com.esliceu.puncher.utils.TimeParser;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class BatchProcess {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private SessionProfessorRepository sessionProfessorRepository;
    @Autowired
    private SessionStudentRepository sessionStudentRepository;
    @Autowired
    private SchoolRoomRepository schoolRoomRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TimeParser timeCalculator;


    public void process(Center center) {

        Map<String, com.esliceu.puncher.data.model.Group> groupsTutor = new HashMap<>();

        Map<Integer, com.esliceu.puncher.data.model.Course> courseById = new HashMap<>();
        Map<Integer, com.esliceu.puncher.data.model.Group> groupsById = new HashMap<>();
        Map<String, com.esliceu.puncher.data.model.Professor> professorById = new HashMap<>();
        Map<Integer, Subject> subjectById = new HashMap<>();
        Map<Long, Student> studentById = new HashMap<>();
        Map<String, Student> studentByCode = new HashMap<>();


        // Sacar los cursos, cada uno de los grupos que tienen esos cursos y meterlos en la base de datos
        for (Courses courses : center.getCourses()) {

            for (com.esliceu.puncher.parser.model.Course course : courses.getCourses()) {

                com.esliceu.puncher.data.model.Course courseDB = new com.esliceu.puncher.data.model.Course();
                courseDB.setCode(course.getCodi());
                courseDB.setDescription(course.getDescripcio());
                courseDB = courseRepository.save(courseDB);
                courseById.put(courseDB.getCode(), courseDB);

                for (com.esliceu.puncher.parser.model.Group group : course.getGroups()) {

                    com.esliceu.puncher.data.model.Group groupDb = new com.esliceu.puncher.data.model.Group();
                    groupDb.setCode(group.getCode());
                    groupDb.setName(group.getName());
                    groupDb.setCourse(courseDB);
                    groupDb = groupRepository.save(groupDb);

                    groupsTutor.put(group.getTutor(), groupDb);
                    groupsById.put(groupDb.getCode(), groupDb);

                }

            }

            System.out.println("Grupos añadidos");
        }

        System.out.println("Cursos añadidos");

        //Sacar todas las asignaturas y guardarlas en la base de datos

        for (Subjects subjects : center.getSubjects()) {
            for (com.esliceu.puncher.parser.model.Subject subject : subjects.getSubjects()) {
                Subject subjectDB = new Subject();
                subjectDB.setCode(subject.getCodi());
                subjectDB.setDescription(subject.getDescripcio());
                com.esliceu.puncher.data.model.Course course = courseById.get(subject.getCurs());
                subjectDB.setCourse(course);

                subjectDB = subjectRepository.save(subjectDB);

                subjectById.put(subjectDB.getCode(), subjectDB);


            }
        }

        System.out.println("Asignaturas añadidas");


        //Sacar todos los professores y  guardarlos en la base de datos

        for (Teachers teachers : center.getProfessors()) {
            for (Teacher teacher : teachers.getTeacher()) {

                Professor professor = new Professor();
                professor.setCode(teacher.getCode());
                professor.setName(teacher.getName());
                professor.setFirstSurname(teacher.getFirstSurname());
                professor.setSecondSurname(teacher.getFirstSurname());
                professor.setGroup(groupsTutor.get(teacher.getCode()));

                professor = professorRepository.save(professor);
                professorById.put(professor.getCode(), professor);

            }

        }

        System.out.println("Profes añadidos");


        //Sacar todos los estudiantes y  guardarlos en la base de datos

        for (int i = 0; i < center.getAlumnes().size(); i++) {
            for (int j = 0; j < center.getAlumnes().get(i).getStudents().size(); j++) {

                Student student = new Student();

                student.setCode(center.getAlumnes().get(i).getStudents().get(j).getCode());
                student.setName(center.getAlumnes().get(i).getStudents().get(j).getName());
                student.setFirstSurname(center.getAlumnes().get(i).getStudents().get(j).getFirstSurname());
                student.setSecondSurname(center.getAlumnes().get(i).getStudents().get(j).getSecondSurname());

                //Recupera el grupo ya creado anteriormente que tenga el mismo numero que el grupo del usuario
                com.esliceu.puncher.data.model.Group auxGroup = groupsById.get(center.getAlumnes().get(i).getStudents().get(j).getGroupCode());
                student.setGroup(auxGroup);

                student = studentRepository.save(student);

                studentById.put(student.getId(), student);
                studentByCode.put(student.getCode(), student);

            }
        }

        System.out.println("Estudiantes añadidos");


        Thread thread = new Thread(() -> {

            for (ScheduleTeachers scheduleTeachers : center.getScheduleTeachers()) {

                int cont = 0;
                for (TeachersSession teachersSession : scheduleTeachers.getTeachersSessions()) {

                    cont++;

                    if (cont % 1000 ==  0) System.out.println("TeachersSession: " + cont + "/" + scheduleTeachers.getTeachersSessions().size() );

                    ProfessorSession professorSession = new ProfessorSession();
                    professorSession.setDay(teachersSession.getDay());

                    String startHour = teachersSession.getHour();
                    professorSession.setStartHour(startHour);

                    Integer durada = teachersSession.getDurada();
                    professorSession.setDurada(durada);

                    DateTime endDate = timeCalculator.addTime(durada, startHour);
                    String endHour = timeCalculator.formatTime(endDate);
                    professorSession.setEndHour(endHour);

                    com.esliceu.puncher.data.model.Course course = courseById.get(teachersSession.getCurs());
                    professorSession.setCourse(course);

                    com.esliceu.puncher.data.model.Subject subject = subjectById.get(teachersSession.getSubmateria());
                    professorSession.setSubject(subject);

                    com.esliceu.puncher.data.model.Group group = groupsById.get(teachersSession.getGroupCode());
                    professorSession.setGroup(group);

                    Professor professor = professorById.get(teachersSession.getProfessorCode());
                    professorSession.setUser(professor);

                    sessionProfessorRepository.save(professorSession);
                }

            }
            System.out.println("Sessiones de profe añadidos");

        }

        );

        thread.start();


        //Sacar todos las sessiones de estudiantes y  guardarlos en la base de datos

        for (ScheduleStudents scheduleStudents : center.getScheduleStudents()) {
            int cont = 0;
            for (com.esliceu.puncher.parser.model.StudentSession studentSession : scheduleStudents.getStudentSessions()) {

                cont ++;
                if (cont % 1000 == 0) System.out.println("StudentSession: " + cont + "/" + scheduleStudents.getStudentSessions().size() );

                com.esliceu.puncher.data.model.StudentSession studentSessionDB = new com.esliceu.puncher.data.model.StudentSession();
                studentSessionDB.setDay(studentSession.getDay());
                String startHour = studentSession.getHour();
                studentSessionDB.setStartHour(startHour);
                Integer durada = studentSession.getDurada();
                studentSessionDB.setDurada(durada);

                DateTime endDate = timeCalculator.addTime(durada, startHour);
                String endHour = timeCalculator.formatTime(endDate);
                studentSessionDB.setEndHour(endHour);


                Student student = studentByCode.get(studentSession.getStudentCode());
                studentSessionDB.setUser(student);

                com.esliceu.puncher.data.model.Subject subject = subjectById.get(studentSession.getSubmateria());
                studentSessionDB.setSubject(subject);

                sessionStudentRepository.save(studentSessionDB);

            }
        }

        System.out.println("Sessiones de alumno añadidos");

        //Sacar todas las aulas del centro y guardarlas en la base de datos

        for (Classrooms classrooms : center.getClassrooms()) {
            for (Classroom classroom : classrooms.getClassrooms()) {

                SchoolRoom schoolRoom = new SchoolRoom();
                schoolRoom.setCode(classroom.getCodi());
                schoolRoom.setDescription(classroom.getDescripcio());
                schoolRoomRepository.save(schoolRoom);

            }
        }

    }
}
