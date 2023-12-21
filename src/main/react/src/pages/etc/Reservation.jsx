import React, { useState } from "react";
import TimePicker from "react-time-picker";
import Moment from "react-moment";
import "react-time-picker/dist/TimePicker.css";
import styled from "styled-components";

// Styled Components를 사용하여 스타일을 정의합니다.
const AppContainer = styled.div`
  text-align: center;
  margin-top: 50px;
`;

const TimePickerContainer = styled.div`
  margin-top: 20px;
`;

const SelectedTime = styled.div`
  margin-top: 20px;
  font-size: 18px;
`;

const Button = styled.button`
  margin-top: 20px;
  padding: 10px 20px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;

function Reservation() {
  const [selectedTime, setSelectedTime] = useState(new Date());

  const handleTimeChange = (newTime) => {
    setSelectedTime(newTime);
  };

  const handleBooking = () => {
    // 여기에서 선택된 시간을 예약하거나 다른 작업을 수행할 수 있습니다.
    alert(`운동 시간이 ${selectedTime}에 예약되었습니다.`);
  };

  return (
    <AppContainer>
      <h1>운동 시간 예약</h1>
      <TimePickerContainer>
        <TimePicker
          onChange={handleTimeChange}
          value={selectedTime}
          disableClock={true}
        />
      </TimePickerContainer>
      <SelectedTime>
        <p>선택된 시간:</p>
        <Moment format="HH:mm">{selectedTime}</Moment>
      </SelectedTime>
      <Button onClick={handleBooking}>예약하기</Button>
    </AppContainer>
  );
}

export default Reservation;
