import styled from "styled-components";
import { useState, useCallback } from "react";
import { MdAdd } from "react-icons/md";

const CateInsertForm = styled.form`
  display: flex;
  background: #495057;
`;

const Input = styled.input`
  background: none;
  outline: none;
  border: none;
  padding: 0.5rem;
  font-size: 1.125rem;
  line-height: 1.5;
  color: white;
  flex: 1;

  &::placeholder {
    color: #dee2e6;
  }
`;

const Button = styled.button`
  background: none;
  outline: none;
  border: none;
  background: #868e96;
  color: white;
  padding-left: 1rem;
  padding-right: 1rem;
  font-size: 1.5rem;
  display: flex;
  align-items: center;
  cursor: pointer;
  transition: 0.1s all ease-in;

  &:hover {
    background: orangered;
  }
`;

const CateInsert = ({ onInsert }) => {
  const [value, setValue] = useState("");

  const onChange = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  const onSubmit = useCallback(
    (e) => {
      onInsert(value);
      setValue("");
      e.preventDefault();
    },
    [onInsert, value]
  );

  return (
    <CateInsertForm onSubmit={onSubmit}>
      <Input
        placeholder="게시글 카테고리 입력"
        value={value}
        onChange={onChange}
      />
      <Button type="submit">
        <MdAdd />
      </Button>
    </CateInsertForm>
  );
};

export default CateInsert;
