import { useContext } from "react";
import { Button } from "react-bootstrap";
import { MyUserContext } from "../../App";

const AfterQuiz = () => {
    const user = useContext(MyUserContext);
    return (
        <>
            <div className="container mt-3">
                <h1 style={{textAlign: "center"}}>Congratuation! {user.username}</h1>
                <Button>See result</Button>
            </div>
            
        </>
    );
}
export default AfterQuiz;