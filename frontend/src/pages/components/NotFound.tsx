import React from 'react';
import Lottie from 'react-lottie';
import NotFoundAnimation from 'src/animation/94905-404-not-found.json';

export const NotFound = () => {
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: NotFoundAnimation,   
        rendererSettings: {
          preserveAspectRatio: "xMidYMid slice"
        }
      };
    
    return (
      <><br/><br/>
        <Lottie 
          source={require('src/animation/94905-404-not-found.json')}
          options={defaultOptions}
          height={400}
          width={400}
        />
      </>
    )
}