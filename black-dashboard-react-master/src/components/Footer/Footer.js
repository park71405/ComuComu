/*!

=========================================================
* Black Dashboard React v1.2.2
=========================================================

* Product Page: https://www.creative-tim.com/product/black-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/black-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container, Nav, NavItem, NavLink } from "reactstrap";

function Footer() {
  return (
    <footer className="footer">
      <Container fluid>
        <Nav>
          <NavItem>
            <NavLink href="https://www.notion.so/park71403/8a7680c38ee9460ca6b59bcfc8e677aa">
              주식회사 ComuComu
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="https://www.notion.so/park71403/8a7680c38ee9460ca6b59bcfc8e677aa">
              About Us
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="https://www.notion.so/park71403/8a7680c38ee9460ca6b59bcfc8e677aa">
              Blog
            </NavLink>
          </NavItem>
        </Nav>
        <div className="copyright">
          <a
            href="https://www.notion.so/park71403/8a7680c38ee9460ca6b59bcfc8e677aa"
            target="_blank"
          >
            ComuComu
          </a>
        </div>
      </Container>
    </footer>
  );
}

export default Footer;
