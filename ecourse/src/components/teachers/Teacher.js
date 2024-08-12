import { useEffect, useState } from "react";
import { Card, Col, Row } from "react-bootstrap";
import APIs, { endpoints } from "../../configs/APIs";
import { Link, useNavigate } from "react-router-dom";

const Teacher = () => {
    const [teachers, setTeachers] = useState([]);
    const navigate = useNavigate();

    const loadTeacher = async () => {
        let res = await APIs.get(endpoints['teachers']);
        setTeachers(res.data);
    }
    
    const handleCardClick = (id) => {
        navigate(`/teachers/${id}`);
    };

    useEffect(() => {
        loadTeacher();
    }, [])

    return (
        <>
        <div className="container">
          <Row>
            {teachers.map((t, index) => (
              <Col key={t.id} md={3} className="mb-4 d-flex">
                <Card className="w-100" style={{ height: '550px' }} >
                  <Card.Img variant="top" src={t.user?.avatar} className="square-img" style={{ height: '200px', objectFit: 'cover' }} />
                  <Card.Body className="d-flex flex-column">
                    <Card.Title className="font-size-bold">{t.user?.username}</Card.Title>
                    <Card.Title style={{ color: "#68A7AD" }}>{t.position}</Card.Title>
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

            {/* <div className="container">
                <Row>
                    {teachers.map((t, index) => (
                        <Col key={t.id} md={3} className="mb-4 ">
                            <Card  style={{height: "600px;"}} >
                                <Card.Img variant="top" src={t.user?.avatar} className="square-img" />
                                <Card.Body>
                                <Card.Title className="font-size-bold">{t.user?.username}</Card.Title>
                                <Card.Title style={{ color: "#68A7AD" }}>{t.position}</Card.Title>
                                <Card.Text className="text-truncate">
                                    {t.description}
                                </Card.Text>
                                </Card.Body>
                                <Card.Footer className="d-flex" style={{ justifyContent: "space-around" }} 
                                    onClick={() => handleCardClick(t.id)}>
                                <Link to="/" className="nav-link button-color font-size-header design-button">More details</Link>
                                </Card.Footer>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </div> */}
           
        </>
    );
}

export default Teacher;