import firebase from "firebase/compat/app"; // firebase를 사용하기 위해 import
import "firebase/compat/storage"; // storage를 사용하기 위해 import

const firebaseConfig = {
  apiKey: "AIzaSyDqc3jZo2Kj2bgAVgxTC9su0P1N9kLe-Hg",
  authDomain: "kh-mini-project.firebaseapp.com",
  projectId: "kh-mini-project",
  storageBucket: "kh-mini-project.appspot.com",
  messagingSenderId: "106733687469",
  appId: "1:106733687469:web:f094788d000284bafa6c24",
  measurementId: "G-1RW6SQ37MW",
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig); // firebase를 초기화 시키는 코드
export const storage = firebase.storage(); // storage를 사용하기 위해 변수에 담아줌
