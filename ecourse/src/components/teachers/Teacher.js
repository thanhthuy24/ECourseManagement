import { useEffect, useState } from "react";
import { Card, CardGroup } from "react-bootstrap";
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
            <CardGroup className="container">
                {teachers.map(t => 
                <Card key={t.id} onClick={() => handleCardClick(t.id)}>
                    <Card.Img variant="top" src={t.user?.avatar} className="square-img"/>
                    <Card.Body>
                        <Card.Title className="font-size-bold">{t.user?.username}</Card.Title>
                        <Card.Title style={{color: "#68A7AD"}}>{t.position}</Card.Title>
                        <Card.Text>
                            {t.description}
                        </Card.Text>
                    </Card.Body>
                    <Card.Footer className="d-flex" style={{justifyContent: "space-around"}}>
                        <Link to="/" className="nav-link button-color font-size-header design-button">More details</Link>
                    </Card.Footer>
                </Card>
                )}
                
            </CardGroup>
        </>
    );
}

export default Teacher;