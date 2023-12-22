import React, { useEffect, useRef } from "react";
import styled from "styled-components";
import useWeather from "../../hooks/useWeather";

const MapContainer = styled.div`
  width: 100%;
  height: 100vh;
`;

const KakaoMap = () => {
  const { location } = useWeather();
  const mapRef = useRef(null);

  console.log(location);
  useEffect(() => {
    const container = mapRef.current;
    const options = {
      center: new window.kakao.maps.LatLng(location.lat, location.long), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

    const map = new window.kakao.maps.Map(container, options);
    const marker = new window.kakao.maps.Marker({
      position: new window.kakao.maps.LatLng(location.lat, location.long), // 마커의 위치
    });
    marker.setMap(map);
  }, [location]);

  return (
      <div>
        <MapContainer ref={mapRef}></MapContainer>
      </div>
  );
};

export default KakaoMap;