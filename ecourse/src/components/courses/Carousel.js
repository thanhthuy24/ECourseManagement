// import Slider from "react-slick";
// import React from "react";
import './styleCarousel.css';
// const Carousel = () => {
//   const settings = {
//     dots: true,
//     infinite: true,
//     speed: 500,
//     slidesToShow: 1,
//     slidesToScroll: 1,
//     autoplay: true,
//     autoplaySpeed: 2000,
//   };
//   return (
//     <>
//       <div className="carousel-container">
//         <Slider {...settings}>
//           <div>
//             <img
//               src="https://res.cloudinary.com/dps7wzdje/image/upload/v1723013652/eosgiplchfmvrda7wogw.jpg"
//               alt="Slide 1"
//             />
//           </div>
//           <div>
//             <img
//               src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720103920/vxddunat6pe2dhzrmqqh.jpg"
//               alt="Slide 2"
//             />
//           </div>
//           <div>
//             <img
//               src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720106880/jcewotjodtm7mdgvmdb4.jpg"
//               alt="Slide 3"
//             />
//           </div>
//           <div>
//             <img
//               src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720693797/lr7tra02kl6kwit70kua.jpg"
//               alt="Slide 4"
//             />
//           </div>
//         </Slider>
//       </div>
//     </>
//   );
// };
// export default Carousel;
import Carousel from 'react-bootstrap/Carousel';

function DarkVariantExample() {
  return (
    <Carousel data-bs-theme="dark" >
      <Carousel.Item className='slick-slide' >
        <img
          className="d-block w-100"
          src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720693797/lr7tra02kl6kwit70kua.jpg"
          alt="First slide"
        />
      </Carousel.Item>
      <Carousel.Item className='slick-slide'>
        <img
          className="d-block w-100"
          src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720106880/jcewotjodtm7mdgvmdb4.jpg"
          alt="Second slide"
        />
      </Carousel.Item>
      <Carousel.Item className='slick-slide'>
        <img
          className="d-block w-100"
          src="https://res.cloudinary.com/dps7wzdje/image/upload/v1720103920/vxddunat6pe2dhzrmqqh.jpg"
          alt="Third slide"
        />
      </Carousel.Item>
    </Carousel>
  );
}

export default DarkVariantExample;