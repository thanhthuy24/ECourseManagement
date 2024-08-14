import { useEffect, useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import { Link, Navigate, useSearchParams } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import { format } from 'date-fns';

const Courses = () => {
    const [courses, setCourses] = useState([]);
    const [q] = useSearchParams(); 
    const [page, setPage] = useState(1);

    const loadCourses = async () => {
        try {
            let url = `${endpoints['courses']}?page=${page}`;
            
            let cateId = q.get("cateId")

            if (cateId != null) {
                setPage(1);
                url = `${url}&cateId=${cateId}`;
            }

            let k = q.get("kw");
            if (k != null) {
                setPage(1);
                url = `${url}&q=${k}`;
            }

            let res = await APIs.get(url);
            if (page === 1)
                setCourses(res.data);
            else
            setCourses(current => [...current, ...res.data]); 

        } catch(err) {
            console.error(err);
        }
        
    }

    useEffect(() => {
        loadCourses();
    }, [q, page])

    const loadMore = () => {
        setPage(page + 1);
    }

    const handleCardClick = (id) => {
        Navigate(`/courses/${id}`);
    };

    return (
        <>
            <div className="container">
                <Row>
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
                               onClick={() => handleCardClick(t.id)} 
                                className="d-flex" 
                                style={{ justifyContent: "space-around" }}>
                            <Link to="/" className="nav-link button-color font-size-header design-button">More details</Link>
                        </Card.Footer>
                        </Card>
                    </Col>
                    ))}
                </Row>
            </div>
        </>
    );
}

export default Courses;