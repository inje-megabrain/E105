import React from "react";
import Lottie from 'react-lottie';
import LoadingAnimation from 'src/animation/51-preloader.json';

export const Loading = () => {

    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: LoadingAnimation,
      };

    return(
        <>
            <Lottie
                isClickToPauseDisabled
                options={defaultOptions} />
        </>
    )
}