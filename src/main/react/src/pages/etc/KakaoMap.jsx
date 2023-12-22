import React, { useEffect, useRef, useState } from "react";
import styled from "styled-components";
import useWeather from "../../hooks/useWeather";

const MapContainer = styled.div`
  width: 100%;
  height: 90vh;
`;

const AppContainer = styled.div`
  display: flex;
  position: relative;
  flex-direction: column;
  align-items: center;
`;

const SearchContainer = styled.div`
  position: fixed;
  top: 60px;
  right: 20px;
  background-color: #fff;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  z-index: 1;
  display: flex; /* 수평 정렬을 위해 flex 사용 */
  align-items: center; /* 수직 중앙 정렬 */
`;

const InputWrapper = styled.div`
  flex: 1; /* 입력창이 확장되도록 함 */
  display: flex; /* 내부 요소를 수평으로 배치하기 위해 flex 사용 */
  align-items: center; /* 수직 중앙 정렬 */
`;

const InfoWindowContainer = styled.div`
  position: fixed;
  top: 140px;
  right: 20px;
  background-color: #fff;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  z-index: 1;
  width: 260px;
  justify-content: center;
`;

const Input = styled.input`
  width: 100%; /* 입력창이 전체 너비를 차지하도록 함 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
`;

const Button = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 10px;
  cursor: pointer;
  font-size: 14px;
  margin-left: 10px;
`;

const InfoWindowContent = styled.div`
  padding: 10px;
  font-size: 16px;
`;

const KakaoMap = () => {
  const { location } = useWeather();
  const mapRef = useRef(null);
  const infoWindowRef = useRef(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [map, setMap] = useState(null);
  const [markers, setMarkers] = useState([]);
  const [selectedPlace, setSelectedPlace] = useState(null);

  useEffect(() => {
    const container = mapRef.current;
    const options = {
      center: new window.kakao.maps.LatLng(location.lat, location.long),
      level: 3,
    };

    const kakaoMap = new window.kakao.maps.Map(container, options);
    setMap(kakaoMap);
  }, [location]);

  const handleSearchInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchButtonClick = () => {
    if (map && searchQuery) {
      const places = new window.kakao.maps.services.Places();

      markers.forEach((marker) => marker.setMap(null)); //  마커 지우기

      places.keywordSearch(searchQuery, (data, status) => {
        console.log("handleSearchButtonClick : " + searchQuery);
        if (status === window.kakao.maps.services.Status.OK) {
          const newMarkers = [];
          for (let i = 0; i < data.length; i++) {
            const place = data[i];
            console.log(place);
            const placeMarker = new window.kakao.maps.Marker({
              position: new window.kakao.maps.LatLng(place.y, place.x),
            });

            placeMarker.setMap(map);

            window.kakao.maps.event.addListener(placeMarker, "click", () => {
              setSelectedPlace(place);

              // InfoWindow 팝업 스타일링 및 표시
              const infoWindow = new window.kakao.maps.InfoWindow({
                content: (
                  <InfoWindowContent>
                    <strong>{place.place_name}</strong>
                    <br />
                    주소: {place.address_name}
                    <br />
                    전화번호: {place.phone}
                  </InfoWindowContent>
                ),
              });

              // 이전 InfoWindow 닫기
              if (infoWindowRef.current) {
                infoWindowRef.current.close();
              }

              infoWindow.open(map, placeMarker);
              infoWindowRef.current = infoWindow;
            });

            newMarkers.push(placeMarker);
          }
          setMarkers(newMarkers);
        }
      });
    }
  };

  return (
    <AppContainer>
      <MapContainer ref={mapRef}></MapContainer>
      <SearchContainer>
        <InputWrapper>
          <Input
            type="text"
            placeholder="검색할 장소를 입력하세요"
            value={searchQuery}
            onChange={handleSearchInputChange}
            onKeyPress={(e) => {
              if (e.key === "Enter") {
                handleSearchButtonClick();
              }
            }}
          />
        </InputWrapper>
        <Button onClick={handleSearchButtonClick}>확인</Button>
      </SearchContainer>
      {selectedPlace && (
        <InfoWindowContainer>
          <InfoWindowContent>
            <strong>{selectedPlace.place_name}</strong>
            <br />
            주소: {selectedPlace.address_name}
            <br />
            전화번호: {selectedPlace.phone}
          </InfoWindowContent>
        </InfoWindowContainer>
      )}
    </AppContainer>
  );
};

export default KakaoMap;
