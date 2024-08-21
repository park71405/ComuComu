import { useEffect, useState } from "react";
import { Button, ButtonGroup, ButtonToolbar } from "reactstrap";

function PageManage(props) {
  const totalPage = Math.ceil(props.boardCount / 5);
  const [start, setStart] = useState(1);
  const noPrev = start == 1;
  const noNext = start + 5 - 1 >= totalPage;

  useEffect(() => {
    if (props.page == start + 5) setStart((prev) => prev + 5);
    if (props.page < start) setStart((prev) => prev - 5);
  }, [props.page, start]);

  return (
    <>
      <ButtonToolbar className="justify-content-center ">
        <ButtonGroup>
          {props.page == 1 ? "" : <Button>&lt;</Button>}
          {[...Array(5)].map((a, i) => {
            return (
              <>
                {start + i <= totalPage && <Button>{start + i}</Button>}
              </>
            );
          })}
          {totalPage > 5 && props.page != totalPage ? (
            <Button>&gt;</Button>
          ) : (
            ""
          )}
        </ButtonGroup>
      </ButtonToolbar>
    </>
  );
}

export default PageManage;
