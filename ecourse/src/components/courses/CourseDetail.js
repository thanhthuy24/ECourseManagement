import { useEffect, useState } from "react";
import { useParams } from "react-router";
import APIs, { endpoints } from "../../configs/APIs";
import { Button, Col, Image, Row, Spinner } from "react-bootstrap";
import moment from 'moment';
const CourseDetail = () => {
    const { id } = useParams();
    const [course, setCourse] = useState([]);

    const loadCourse = async () => {
        let res = await APIs.get(endpoints['course'](id));
        setCourse(res.data);
        console.log(res.data);
    }

    useEffect(() => {
        loadCourse();
    }, [id])

    return (
        <>
        <div className="container">
            <Row>
                <Col>
                    {course === null ? 
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    : <>
                        {/* <h2>{course.id}</h2> */}
                        <Button className="font-size-bold margin btn-success">{course.tag?.name}</Button>
                        <h3 className="font-size-bold margin font-teacher-username">{course.name}</h3>
                        <h3 className="margin ">Price: {course.price}</h3>
                        <h3 className="margin font-teacher-description border-description">
                            {course.description}
                        </h3>
                        <h3 className="margin">Created date: {moment(course.createdDate).format('DD/MM/YYYY')}</h3>
                        {/* <Button ref={target} className="button-contact" onClick={() => handleCopy(teacher.user?.email)} >{teacher.user?.email}</Button> */}
                        
                    </>    
                    }
                </Col>
                <Col className="margin">
                    <Image className="image" src={course.image} />
                </Col>
            </Row>
            </div>
        </>
    );
}

export default CourseDetail;