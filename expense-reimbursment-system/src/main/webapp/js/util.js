class Util {
  getHeader(authority) {
    let headerHTML = "";
    let html1 = `
	<header>
		<nav class=" my-nav navbar navbar-light navbar-expand-lg ">
			<a class="navbar-brand" style="height: 20%; width:10%" href="/ers/login"><img src="/ers/images/rev3.png" height="100%" width="100%"></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">`;

    let htmlManagerItems = `
					<li class="nav-item">
						<a class="nav-link" href="/ers/manager/all-employees">All employees</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/ers/manager/register-employee">Register Employee</a>
					</li>`;
    let htmlEmployeeItems1 = `
					<li class="nav-item">
						<a class="nav-link" href="/ers/employee/reimbursement">Employee Reiumbursement</a>
					</li>`;

    let html2 = `
					<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"><i class="fa fa-user-circle"></i>
						</a>
						<div class="dropdown-menu dropdown-menu-right " aria-labelledby="navbarDropdownMenuLink"
							style="min-width: 20px;">`;

    let htmlEmployeeItems2 = `
			  				<a class="dropdown-item" href="/employee/profile">Profile</a>`;
    let html3 = `
							<form action="/ers/logout" method="post" class=" dropdown-item">
								<button class="dropdown-item" style="padding: 0;">
								Logout
								</button>
							</form>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	</header>`;
    headerHTML += html1;
    if (authority == "MANAGER") {
      headerHTML += htmlManagerItems + html2 + html3;
    } else {
      headerHTML += htmlEmployeeItems1 + html2 + htmlEmployeeItems2 + html3;
    }
    return headerHTML;
  }

  getFooter() {
    let footerHTML = `
	<div class = "container">
		<hr>
		<footer class="mastfoot mt-auto" >
			<div class="inner app-footer" >
			  <p style = "text-align: center; color: blue;">&copy;2019 | <b>Samuel Dorilas</b>  All rights reserved</p>
			</div>
		</footer>
	</div>`;
    return footerHTML;
  }

  getheaderLinks() {
    let links = `
  		<link rel="stylesheet" href="/ers/bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet"href="/ers/fontawesome/css/all.min.css">
		<link rel="shortcut icon" type="image/png" href="/ers/images/favicon.ico" />`;
    return links;
  }

  getJsScripts() {
    let scripts = `
	  	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
			integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
			crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
			integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
			crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
			integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
			crossorigin="anonymous"></script>`;
	let webjars = `
			<script src="/ers/webjars/jquery/3.4.1/jquery.min.js"></script>
			<script src="/ers/webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>`;
	return scripts;
  }
}
