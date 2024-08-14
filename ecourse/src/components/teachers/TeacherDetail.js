import { useEffect, useRef, useState } from "react";
import { Button, Col, Image, Overlay, Row, Spinner, Tooltip } from "react-bootstrap";
import { useParams } from "react-router";
import APIs, { endpoints } from "../../configs/APIs";
import './styleTeacher.css';

const TeacherDetail = () => {
    const [teacher, setTeacher] = useState([]);
    const { id } = useParams();

    const [showEmail, setShowEmail] = useState(false);
    const targetE = useRef(null);
    const [showPhone, setShowPhone] = useState(false);
    const targetP = useRef(null);
    

    const loadTeacher = async () => {
        let res = await APIs.get(endpoints['teacher'](id));
        setTeacher(res.data);
    }

    useEffect(() => {
        loadTeacher();
    }, [id]);

    const handleCopy = (text, setShow) => {
        // const textToCopy = teacher.user?.email || '';
        navigator.clipboard.writeText(text || '').then(() => {
            setShow(true);
            setTimeout(() => setShow(false), 1500);
        }).catch(err => {
            console.error('Sao chép thất bại', err);
        });
    };

    return (
        <>
            <div className="container">
            <Row>
                <Col>
                    {teacher === null ? 
                        <Spinner animation="border" role="status">
                            <span className="visually-hidden">Loading...</span>
                        </Spinner>
                    : <>
                        <Button className="font-size-bold margin btn-success">Instructor</Button>
                        <h3 className="font-size-bold margin font-teacher-username">{teacher.user?.username}</h3>
                        <h3 className="margin">Position: {teacher.position}</h3>
                        <h3 className="margin font-teacher-description border-description">
                            {teacher.description}
                        </h3>
                        <h3 className="margin">Contact here: </h3>
                        {/* <Button ref={target} className="button-contact" onClick={() => handleCopy(teacher.user?.email)} >{teacher.user?.email}</Button> */}
                        <Button
                            ref={targetE}
                            className="button-contact"
                            onClick={() => handleCopy(teacher.user?.email, setShowEmail)}
                        >
                            {teacher.user?.email}
                        </Button>
                        <Overlay target={targetE.current} show={showEmail} placement="bottom">
                            {(props) => (
                                <Tooltip id="overlay-example" {...props}>
                                    Đã sao chép!
                                </Tooltip>
                            )}
                        </Overlay>
                         <Button 
                         ref={targetP}
                            className="button-contact"
                            onClick={() => handleCopy(teacher.user?.phoneNumber, setShowPhone)}
                        >
                            {teacher.user?.phoneNumber}
                        </Button>
                        <Overlay target={targetP.current} show={showPhone} placement="bottom">
                            {(props) => (
                                <Tooltip id="overlay-example" {...props}>
                                    Đã sao chép!
                                </Tooltip>
                            )}
                        </Overlay>
                    </>    
                    }
                </Col>
                <Col className="margin">
                    <Image className="image" src={teacher.user?.avatar} />
                </Col>
            </Row>
            </div>
        </>
    );
}

export default TeacherDetail;