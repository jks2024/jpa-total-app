import React, { useState } from "react";
import styled from "styled-components";

// 스타일드 컴포넌트 정의
const MenuContainer = styled.div`
  overflow: hidden;
  background-color: lightgray;
  border-radius: 10px;
  padding: 10px;
  margin: 0 20px; // 양끝에 마진 추가
  position: relative;
`;

const MenuWrapper = styled.div`
  display: flex;
  transition: transform 0.5s ease-in-out;
`;

const MenuItem = styled.span`
  flex: 0 0 100%; // 각 항목이 전체 컨테이너의 폭을 차지하도록 설정
  text-align: center;
`;

const ArrowButton = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  z-index: 1;
`;

const SwipeTest = () => {
  const girlGroups = [
    "블랙핑크",
    "트와이스",
    "레드벨벳",
    "마마무",
    "아이즈원",
    "잇지",
    "에스파",
    "오마이걸",
    "여자친구",
    "러블리즈",
  ];
  const [currentIndex, setCurrentIndex] = useState(0);

  const handleSwipe = (direction) => {
    if (direction === "left") {
      setCurrentIndex((prevIndex) =>
        prevIndex === 0 ? girlGroups.length - 1 : prevIndex - 1
      );
    } else {
      setCurrentIndex((prevIndex) =>
        prevIndex === girlGroups.length - 1 ? 0 : prevIndex + 1
      );
    }
  };

  return (
    <MenuContainer>
      <ArrowButton style={{ left: "10px" }} onClick={() => handleSwipe("left")}>
        <svg width="24" height="24" viewBox="0 0 24 24">
          <path
            d="M15 18l-6-6 6-6"
            stroke="#000"
            strokeWidth="2"
            fill="none"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
        </svg>
      </ArrowButton>
      <MenuWrapper
        style={{
          transform: `translateX(${-100 * currentIndex}%)`,
          transition: "transform 0.5s ease-in-out",
        }}
      >
        {girlGroups.map((group, index) => (
          <MenuItem key={index}>{group}</MenuItem>
        ))}
      </MenuWrapper>
      <ArrowButton
        style={{ right: "10px" }}
        onClick={() => handleSwipe("right")}
      >
        <svg width="24" height="24" viewBox="0 0 24 24">
          <path
            d="M9 6l6 6-6 6"
            stroke="#000"
            strokeWidth="2"
            fill="none"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
        </svg>
      </ArrowButton>
    </MenuContainer>
  );
};

export default SwipeTest;
