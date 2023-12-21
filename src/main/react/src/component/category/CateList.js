import CateListItem from "./CateListItem";
import styled from "styled-components";

const CateListItemContainer = styled.div`
  min-height: 320px; // 내용이 적더라도 최소 320px 유지
  max-height: 513px; // 내용이 많더라도 최대 513px 유지
  overflow-y: auto; // 지정한 높이를 초과할 때 수직 스크롤바 생김
`;

const CateList = ({ cates, onRemove }) => {
  return (
    <CateListItemContainer>
      {cates.map((cate) => (
        <CateListItem todo={cate} key={cate.id} onRemove={onRemove} />
      ))}
    </CateListItemContainer>
  );
};

export default CateList;
