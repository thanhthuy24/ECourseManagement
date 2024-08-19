import cookie from "react-cookies";

const MyCartReducer = (currentState, action) => {
    if(action.type === 'update'){
        let cart = cookie.load("cart") || null;
        if(cart != null) {
            let totalQuantity = 0;
            for (let c of Object.values(cart))
                totalQuantity += c.quantity;
            return totalQuantity;
        }
    } else if (action.type === "paid") return 0;
    return currentState;
} 

export default MyCartReducer;