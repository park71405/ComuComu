import { useEffect, useState } from "react";
import { Button, ButtonGroup, ButtonToolbar } from "reactstrap";

function PageManage(props) {
  const totalPage = Math.ceil(props.boardCount / 5);
  const [start, setStart] = useState(1);

  useEffect(() => {
    if (props.page == start + 5) setStart((prev) => prev + 5);
    if (props.page < start) setStart((prev) => prev - 5);
  }, [props.page, start]);

  const clickPage = (value) => {
    props.pageHandler(value);
  };

  const clickRPage = () => {
    let nextPage = (Math.ceil(props.page / 5) * 5) + 1;
    console.log("nextPage " + nextPage);
    props.pageHandler(nextPage);
  };

  const clickLPage = () => {
    let prevPage = ((Math.ceil(props.page / 5) * 5)) - 5;
    props.pageHandler(prevPage);
  }

  return (
    <>
      <ButtonToolbar className="justify-content-center ">
        <ButtonGroup>
          {props.page != 1 && props.page > 5 ? <Button onClick={clickLPage} >&lt;</Button> : ""}
          {[...Array(5)].map((a, i) => {
            return (
              <>
                {start + i <= totalPage && (props.page == start+i ? <Button className="btn-info" key={i} onClick={()=>clickPage(start+i)}>{start + i}</Button> : <Button key={i} onClick={()=>clickPage(start+i)}>{start + i}</Button>)}
              </>
            );
          })}
          {(Math.ceil(props.page / 5) * 5) + 1 <= totalPage ? (
            <Button onClick={clickRPage}>&gt;</Button>
          ) : (
            ""
          )}
        </ButtonGroup>
      </ButtonToolbar>
    </>
  );
}

export default PageManage;
