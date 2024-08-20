import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { authAPIs, endpoints } from "../configs/APIs";
import { Button, Card, Image} from "react-bootstrap";
import { format } from "date-fns";

const UserCourseDetail = () => {
    const { id } = useParams();
    const [courses, setCourses] = useState([]);
    const nav = useNavigate();
    // const history = useHistory();

    const loadReceiptDetail = async () => {
        try{
            let res = await authAPIs().get(endpoints['my-courses'](id));
            setCourses(res.data);
        } catch(err) {
            console.error(err);
        }
    }

    useEffect(() => {
        loadReceiptDetail();
    }, [id])

    const handleClick = (courseId) => {
        // console.log(`${courseId}`);
        nav(`/lessons/${courseId}`);
    }

    

    return (
        <> 
            <div className="container">
            {courses.length === 0 ? (
                <p>No courses found.</p>
            ) : (
                
                <div>
                    {courses.map((course) => (
                    <Card style={{marginBottom: "20px"}}>
                        <Card.Header>Tên khóa học: {course.courseId?.name}</Card.Header>
                        <Card.Body>
                            <Card.Title>Số tiền: {course.price} VND</Card.Title>
                            <Card.Text className="d-flex" style={{justifyContent: "space-around"}}>
                                <div>
                                <Image className="image" src={course.courseId?.image} />
                                </div>
                                <div>
                                    Mô tả khóa học: {course.courseId?.description}
                                </div>
                               
                            </Card.Text>
                           
                        </Card.Body>
                        <Card.Footer className="text-muted">
                            <p>Ngày tạo: {format(course.courseId?.createdDate, 'dd/MM/yyyy')}</p>
                            <p>Ngày cập nhật gần nhất: {course.courseId?.updatedDate 
                                ? format(new Date(course.courseId.updatedDate), 'dd/MM/yyyy') 
                                : 'Chưa cập nhật'}
                            </p>
                            {/* <p>{course.courseId?.id}</p> */}
                        </Card.Footer>
                         <Button onClick={() => handleClick(course.courseId?.id)} variant="primary">Truy cập khóa học</Button>
                        </Card>
                    ))}
                </div>
            )}
            </div>
           
            
        </>
    );
}

export default UserCourseDetail;