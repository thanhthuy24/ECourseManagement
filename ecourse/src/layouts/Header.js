import { useEffect, useState } from "react";
import APIs, { endpoints } from "../configs/APIs";

const Header = () => {
    const [categories, setCategories] = useState([]);

    const loadCates = async () => {
        let res = await APIs.get(endpoints['categories']);
        setCategories(res.data);
    }

    useEffect(() => {
        loadCates();
    }, [])

    return (
        <>
            <h1>
                Đây là header
            </h1>
            <ul>
                {categories.map(c => <li key={c.id}>{c.name}</li>)}
                
            </ul>
        </>
        
    );
}

export default Header;