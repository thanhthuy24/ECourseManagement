import React from "react";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";

const Arrow = ({ className, style, onClick, type }) => (
  <div
    className={className}
    style={{
      ...style,
      display: "block",
      background: "transparent",
      color: "#B1AFFF",
    }}
    onClick={onClick}
  >
    {type === "prev" ? <FaArrowLeft size={30} /> : <FaArrowRight size={30} />}
  </div>
);

export default Arrow;