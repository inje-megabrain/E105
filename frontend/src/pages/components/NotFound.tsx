import React from 'react';
import Lottie from 'react-lottie';
import NotFoundAnimation from 'src/animation/76706-404-error-page.json';

export const NotFound = () => {
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: NotFoundAnimation,
      };
    
    return (
      <>
        <Lottie 
          isClickToPauseDisabled
          style={{justifyContent: 'center'}}
          options={defaultOptions}
        />
      </>
    )
}