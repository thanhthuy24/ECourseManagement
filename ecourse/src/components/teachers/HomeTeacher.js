import { useContext, useEffect, useState } from "react";
import { authAPIs, endpoints } from "../../configs/APIs";
import { MyUserContext } from "../../App";
import { Button, Card, Col, Row } from "react-bootstrap";
import { format } from "date-fns";
import { useNavigate } from "react-router";

const HomeTeacher = () => {
    const [teachers, setTeachers] = useState([]);
    const [courses, setCourses] = useState([]);

    const [teacherLogin, setTeacherLogin] = useState(null);
    const nav = useNavigate();

    const user = useContext(MyUserContext);

    const loadTeachers = async() => {
        let res = await authAPIs().get(endpoints['teachers']);
        setTeachers(res.data);
    }

    const loadCoursesByTeacherId = async (teacherId) => {
        let res = await authAPIs().get(endpoints['courses-by-teacher'](teacherLogin.id));
        setCourses(res.data);
    }

    useEffect(() => {
        loadTeachers();
    }, []);

    useEffect(() => {
        const filtered = teachers.find(teacher => teacher.user?.id === user.id);
        setTeacherLogin(filtered);
        console.log(filtered);
    }, [teachers, user]);

    useEffect(() => {
        if (teacherLogin) {
            loadCoursesByTeacherId(teacherLogin.id);
        }
    }, [teacherLogin]);

    const handleCardClick = (courseId) => {
        const url = endpoints['assignment-by-course'](courseId);
        nav(url);
    }

    return (
        <>
            <div className="container mt-5">
                <h1 className="font-size-bold">Các khóa học đang đảm nhiệm:</h1>
                <Row>
                {/* <ToastContainer /> */}
                    {courses.map(t => (
                    <Col key={t.id} className="mb-4 d-flex" md={3} xs={12}>
                        <Card className="w-100" style={{ height: '550px' }} >
                        <Card.Img variant="top" src={t.image} className="square-img" style={{ height: '200px', objectFit: 'cover' }} />
                        <Card.Body className="d-flex flex-column">
                            <Card.Title className="font-size-bold">{t.name}</Card.Title>
                            <Card.Title style={{ color: "#68A7AD" }}>
                                Ngày tạo: {format(t.createdDate, 'dd/MM/yyyy')}
                                </Card.Title>
                            <Card.Text className="flex-grow-1">
                            {t.description}
                            </Card.Text>
                            
                        </Card.Body>
                        <Card.Footer 
                                
                                className="d-flex" 
                                style={{ justifyContent: "space-around" }}>
                            <Button onClick={() => handleCardClick(t.id)} className="nav-link button-color font-size-header design-button">More details</Button>
                            {/* <Button onClick={() => addToCart(t)} className="nav-link button-color font-size-header design-button">Add to card</Button> */}
                        </Card.Footer>
                        </Card>
                       
                    </Col>
                    ))}
                </Row>
            </div>

        </>
    );
}

export default HomeTeacher;