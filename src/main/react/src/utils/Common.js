import moment from "moment";
import axios from "axios";
import "moment/locale/ko"; // 한글 로컬라이제이션
moment.locale("ko"); // 한글 설정 적용

const Common = {
  KH_DOMAIN: "",
  KH_SOCKET_URL: "ws://localhost:8111/ws/chat",
  KAKAOKEY: "2dda918f299fb6e8325412499bf9a08a",

  timeFromNow: (timestamp) => {
    return moment(timestamp).fromNow();
  },
  formatDate: (timestamp) => {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = ("0" + (date.getMonth() + 1)).slice(-2); // Adds leading 0 if needed
    const day = ("0" + date.getDate()).slice(-2);
    const hour = ("0" + date.getHours()).slice(-2);
    const minute = ("0" + date.getMinutes()).slice(-2);
    return `${year}년 ${month}월 ${day}일 ${hour}시 ${minute}분`;
  },

  getAddrCoordination: async (addr) => {
    try {
      const response = await axios.get(
        `https://dapi.kakao.com/v2/local/search/address.json?query=${addr}`,
        {
          headers: {
            Authorization: `KakaoAK ${Common.KAKAOKEY}`,
          },
        }
      );

      const result = response.data;
      if (result.documents.length > 0) {
        const firstResult = result.documents[0];
        console.log(firstResult);
        console.log(firstResult.address);
        console.log(firstResult.address.y);
        console.log(firstResult.address.x);
        const { x, y } = firstResult.address;
        return { latitude: y, longitude: x };
      } else {
        return null;
      }
    } catch (error) {
      console.error("Error:", error);
      return null;
    }
  },

  getAccessToken: () => {
    return localStorage.getItem("accessToken");
  },
  setAccessToken: (token) => {
    localStorage.setItem("accessToken", token);
  },
  getRefreshToken: () => {
    return localStorage.getItem("refreshToken");
  },
  setRefreshToken: (token) => {
    localStorage.setItem("refreshToken", token);
  },

  // 401 에러 처리 함수
  handleUnauthorized: async () => {
    console.log("handleUnauthorized");
    const refreshToken = Common.getAccessToken();
    const accessToken = Common.getRefreshToken();
    const config = {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    };
    try {
      const res = await axios.post(
        `${Common.KH_DOMAIN}/auth/refresh`,
        refreshToken,
        config
      );
      console.log(res.data);
      Common.setAccessToken(res.data);
      return true;
    } catch (err) {
      console.log(err);
      return false;
    }
  },
};

export default Common;
