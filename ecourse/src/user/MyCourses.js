import { useContext, useEffect, useState } from "react";
import APIs, { authAPIs, endpoints } from "../configs/APIs";
import { MyUserContext } from "../App";
import { Alert, Button, Card } from "react-bootstrap";
import { format } from 'date-fns';
import { useNavigate } from "react-router";

const MyCourses = () => {
    const [courses, setCourses] = useState([]);
    const user = useContext(MyUserContext);
    const nav = useNavigate();

    const loadMyReceipts = async () => {
        let url = `${endpoints['my-receipts'](user.id)}`;

        try {
            let res = await authAPIs().get(url);
            // console.log(res.data);
            setCourses(res.data);
        } catch (error) {
            console.error("Failed to load courses", error);
        }
    };

    const handleClick = (id) => {
        nav(`/receipt/${id}`);
    }

    useEffect(() => { 
        if (user) {
            loadMyReceipts();
        }
    }, [user]);

    return (
         <>
            <div className="container">
                {user === null ? <>
                    <Alert>Login first to see your courses</Alert>
                </>:
                <>
                    {courses.length === 0 ? (
                        <p>No courses found.</p>
                    ) : (
                        
                        <div>
                            <p>Số hóa đơn hiện có: </p>
                            {courses.map((course) => (
                            <Card style={{marginBottom: "20px"}}>
                                <Card.Header>Hóa đơn: {course.id}</Card.Header>
                                <Card.Body>
                                    <Card.Title>Tổng số tiền mua: {course.total} VND</Card.Title>
                                  
                                    <Button onClick={() => handleClick(course.id)} variant="primary">Xem chi tiết</Button>
                                </Card.Body>
                                <Card.Footer className="text-muted">{format(course.createdDate, 'dd/MM/yyyy')}</Card.Footer>
                                </Card>
                            ))}
                        </div>
                    )}
                </>
                }
                
            </div>
            
        </>
    );
}
export default MyCourses;