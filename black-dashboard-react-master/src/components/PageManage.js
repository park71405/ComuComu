import { Button, ButtonGroup, ButtonToolbar, PaginationItem } from "reactstrap";

function PageManage(props) {

  const btnList = [];

  return (
    <>
      <ButtonToolbar className="justify-content-center">
        <ButtonGroup>
          <Button>&lt;</Button>
          <Button>1</Button>
          <Button>2</Button>
          <Button>3</Button>
          <Button>4</Button>
          <Button>5</Button>
          <Button>&gt;</Button>
        </ButtonGroup>
      </ButtonToolbar>
      {props.page}
    </>
  );
}

export default PageManage;
