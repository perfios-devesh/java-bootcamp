<nav
  class="navbar navbar-expand-lg navbar-light"
  style="margin: auto; margin-top: 10px; background-color: white"
>
  <div class="container-fluid">
    <a
      class="navbar-brand"
      id="imageLink"
      href="index.jsp"
      style="margin: -15px; margin-top: -20px;margin-bottom: -17px; margin-right: 10px"
    >
      <img
        src="resources/logo.png"
        alt="Apex Courier"
        width=""
        height="55px"
        class="d-inline-block align-text-top"
      />
    </a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul
        id="clientNavBar"
        class="navbar-nav me-auto mb-2 mb-lg-0"
      >
        <li class="nav-item">
          <a
            class="nav-link"
            id="dashboardLink"
            aria-current="page"
            href="index.jsp"
            >Home</a
          >
        </li>
        <li class="nav-item">
          <a class="nav-link" href="allCouriers.jsp">All Couriers</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<script>
  function logoutUser() {
    sessionStorage.clear();
    window.open("index.jsp", "_self");
  }
</script>
