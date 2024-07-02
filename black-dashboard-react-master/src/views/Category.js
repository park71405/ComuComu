import axios from "axios";
import Swal from "sweetalert2";
import { useEffect, useState } from "react";
import {
  Button,
  Card,
  CardBody,
  CardHeader,
  CardTitle,
  Col,
  Input,
  Modal,
  ModalBody,
  ModalFooter,
  Row,
  Table,
} from "reactstrap";

function Category(props) {
  const [cateList, setCateList] = useState([]);

  // 카테고리 추가 버튼 클릭 관련 로직
  const [addModalShow, setAddModalShow] = useState(false);
  const AddModalHandleClose = () => setAddModalShow(false);
  const AddModalHandleShow = () => setAddModalShow(true);

  // 카테고리 창닫기 버튼 클릭 시 cateForm 값 초기화
  const resetCateForm = () => {
    setCateForm({
      id: "",
      categoryName: "",
      path: "",
      icon: "",
      component: "",
    });
    AddModalHandleClose();
  };

  // 카테고리 추가 Form
  const [cateForm, setCateForm] = useState({
    categoryName: "",
    path: "",
    icon: "",
    component: "",
  });

  const onChangeCateForm = (e) => {
    setCateForm({
      ...cateForm,
      [e.target.name]: e.target.value,
    });
  };

  // 카테고리 모달창 save 버튼 클릭 이벤트
  const addCategory = () => {
    // 카테고리 추가 모달창 닫기
    AddModalHandleClose();

    console.log(cateForm);

    if (cateForm.categoryName == "") {
      alert("카테고리 이름을 입력해주세요");
    } else if (cateForm.component == "") {
      alert("레이아웃을 입력해주세요");
    } else if (cateForm.icon == "") {
      alert("아이콘을 입력해주세요");
    } else if (cateForm.path == "") {
      alert("경로를 입력해주세요");
    } else {
      
      if(cateForm.id === ""){ // 카테고리 추가 비동기 함수 실행
        addCateForm();
      }else{                  // 카테고리 수정 비동기 함수 실행
        updateCateForm();
      }
      
      
    }
  };

  //카테고리 추가 비동기 함수
  const addCateForm = () => {
    axios({
      method: "POST",
      data: cateForm,
      url: "cate",
    })
      .then((res) => {
        console.log(res);
        Swal.fire({
          text: "카테고리가 추가되었습니다.",
        });
      })
      .catch((res) => {
        Swal.fire({
          title: "카테고리 추가 실패",
          text: "잠시 후 다시 시도해주세요",
          icon: "error",
        });
      })
      .finally(() => {
        // 모달창 닫고 cateForm 값 초기화
        resetCateForm();
        searchCategoryAll();
      });
  }

  // 카테고리 수정 비동기 함수
  const updateCateForm = () => {

    let tmpCate = {no: cateForm.id, categoryName: cateForm.categoryName, path: cateForm.path, icon: cateForm.icon, component: cateForm.component}

    axios({
      method: "PUT",
      data: tmpCate,
      url: "cate",
    })
    .then((res) => {
      console.log(res);
      Swal.fire({
        text: "카테고리가 수정되었습니다.",
      });
    })
    .catch((res) => {
      Swal.fire({
        title: "카테고리 수정 실패",
        text: "잠시 후 다시 시도해주세요",
        icon: "error",
      });
    })
    .finally(() => {
      // 모달창 닫고 cateForm 값 초기화
      resetCateForm();
      searchCategoryAll();
    });
  }

  // 카테고리 삭제 비동기 함수
  const deleteCategory = (e) => {
    console.log(e.target);
    console.log();

    // 삭제할 카테고리의 no
    let cateno = e.target.id;

    Swal.fire({
      title: "카테고리를 삭제하시겠습니까?",
      text: "해당 카테고리에 존재하는 게시글 또한 삭제됩니다.",
      icon: "warning",
      confirmButtonText: "Yes",
      denyButtonText: "No",
      showCancelButton: true,
    }).then((res) => {
      if (res.isConfirmed) {
        //삭제 요청 처리

        axios({
          method: "DELETE",
          params: {
            no: cateno,
          },
          url: "cate",
        })
          .then((res) => {
            Swal.fire({
              text: "카테고리가 삭제되었습니다.",
            });
          })
          .catch((res) => {
            Swal.fire({
              title: "카테고리 삭제 실패",
              text: "잠시 후 다시 시도해주세요",
              icon: "error",
            });
          })
          .finally(() => {
            searchCategoryAll();
          });
      } else {
      }
    });
  };

  // 카테고리 조회 비동기 함수
  const searchCategoryAll = () => {
    axios({
      method: "GET",
      url: "cate/searchAll",
    })
      .then((res) => {
        console.log(res.data);

        let list = [];

        res.data.map((response) => {
          return list.push({
            no: response.no,
            name: response.categoryName,
            icon: response.icon,
            path: response.path,
            component: response.component,
          });
        });

        setCateList(list);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 카테고리 수정 아이콘 클릭
  const updateCategory = (e) => {
    // 모달 창 띄우기
    AddModalHandleShow();

    // 클릭한 row의 정보
    const cateNo = e.target.id;
    let updateCate = cateList.find((cate) => cate.no == cateNo);

    let tmpCate = {id: cateNo,
                    categoryName: updateCate.name,
                    path: updateCate.path,
                    icon: updateCate.icon,
                    component: updateCate.component,};

    // 클릭한 row의 카테고리 정보를 수정 form에 넣기
    setCateForm(tmpCate);

  };

  useEffect(() => {
    searchCategoryAll();
  }, []);

  return (
    <>
      <div className="content">
        <Row>
          <Modal isOpen={addModalShow}>
            <ModalBody className="mt-1">
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  카테고리명 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="cateNameInput"
                    placeholder="ex) 카테고리"
                    defaultValue={cateForm.categoryName}
                    style={{ color: "black" }}
                    name="categoryName"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  path :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="catePathInput"
                    placeholder="ex) /category"
                    defaultValue={cateForm.path}
                    style={{ color: "black" }}
                    name="path"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  아이콘 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="cateIconInput"
                    placeholder="ex) 나중에 모달 하나 더 띄우고 선택하도록..."
                    defaultValue={cateForm.icon}
                    style={{ color: "black" }}
                    name="icon"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
              <Row className="m-1 py-1">
                <Col sm={3} className="mt-1">
                  레이아웃 :{" "}
                </Col>
                <Col sm={9}>
                  <Input
                    id="cateComponentInput"
                    placeholder="ex) 나중에 모달 하나 더 띄우고 선택하도록..."
                    defaultValue={cateForm.component}
                    style={{ color: "black" }}
                    name="component"
                    onChange={onChangeCateForm}
                  />
                </Col>
              </Row>
            </ModalBody>
            <ModalFooter className="justify-content-end m-3">
              <Button
                className="m-1"
                size="sm"
                variant="primary"
                onClick={addCategory}
              >
                Save
              </Button>
              <Button size="sm" variant="primary" onClick={resetCateForm}>
                Close
              </Button>
            </ModalFooter>
          </Modal>
          <Col md="12">
            <Card>
              <CardHeader>
                <CardTitle tag="h4">{props.cateInfo.name}</CardTitle>
              </CardHeader>
              <Button className="mx-4" variant="secondary" onClick={AddModalHandleShow}>
                Category Add
              </Button>

              <CardBody>
                {cateList.length === 0 ? (
                  <h3 className="text-center">no data ...</h3>
                ) : (
                  <Table className="tablesorter text-center">
                    <thead className="text-primary">
                      <tr>
                        <th>No.</th>
                        <th>Category Name</th>
                        <th>표기 url</th>
                        <th>아이콘</th>
                        <th>레이아웃</th>
                      </tr>
                    </thead>
                    <tbody>
                      {cateList.map((cate) => {
                        return (
                          <tr key={cate.no}>
                            <td>{cate.no}</td>
                            <td>{cate.name}</td>
                            <td>{cate.path}</td>
                            <td>
                              <i className={cate.icon}></i>
                            </td>
                            <td>{cate.component}</td>
                            <td>
                              <i
                                className="tim-icons icon-settings"
                                id={cate.no}
                                onClick={updateCategory}
                              ></i>
                            </td>
                            <td>
                              <i
                                className="tim-icons icon-trash-simple"
                                id={cate.no}
                                onClick={deleteCategory}
                              ></i>
                            </td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </Table>
                )}
              </CardBody>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  );
}

export default Category;
