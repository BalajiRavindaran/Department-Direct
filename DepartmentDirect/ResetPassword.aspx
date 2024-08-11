<%@ Page Title="ResetPassword" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="ResetPassword.aspx.cs" Inherits="DepartmentDirect.ResetPassword" Async="true" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <!-- FontAwesome for the eye icon -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            display: grid;
            color: #ffffff;
        }

        h2 {
            font-size: 3.1vw;
            font-weight: normal;
        }

        h3, h4, h5, h6, label {
            font-size: 2.1vw;
            color: red;
        }

        .blink {
            font-size: 20px;
            color: red;
            animation: blinker 1.5s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }

        .title {
            padding: 20px 0 0 0;
        }

        .title {
            color: #912338;
            font-size: 50px;  
        }

        .login {
            background: #912338;
            color: white;
        }

        .login:hover {
            color: yellow;
        }

        #forgotPassword {
            color: dodgerblue;
        }

        .form-group {
            position: relative;
        }

        .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
            color: #aaa;
        }

        .toggle-password:hover {
            color: #333;
        }

    </style>

    <br />
    <div class="container">
        <div class="row">
            <div class="col-md-6 mx-auto">

                <div class="card">
                    <div class="card-body">

                        <div class="row">
                            <div class="col">
                                <center>
                                    <img src="assets/students.png" width="150px" />
                                </center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <center>
                                    <h1 class="title">Password Reset</h1>
                                </center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <hr />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">

                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Student ID"></asp:TextBox>
                                </div>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox2" runat="server" placeholder="Original Password" TextMode="Password"></asp:TextBox>
                                    <span class="toggle-password" onclick="togglePasswordVisibility('<%= TextBox2.ClientID %>')">
                                        <i class="fas fa-eye"></i>
                                    </span>
                                </div>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox3" runat="server" placeholder="New Password" TextMode="Password"></asp:TextBox>
                                    <span class="toggle-password" onclick="togglePasswordVisibility('<%= TextBox3.ClientID %>')">
                                        <i class="fas fa-eye"></i>
                                    </span>
                                </div>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox4" runat="server" placeholder="Confirm new Password" TextMode="Password"></asp:TextBox>
                                    <span class="toggle-password" onclick="togglePasswordVisibility('<%= TextBox4.ClientID %>')">
                                        <i class="fas fa-eye"></i>
                                    </span>
                                </div>
                                <br />
                                <center>
                                    <asp:Label ID="Label1" CssClass="blink" runat="server" Text="This is a text" Visible="False"></asp:Label>
                                </center>
                                <br />
                                <div class="form-group">
                                    <asp:Button CssClass="btn btn-block login" ID="Button1" runat="server" Text="Reset" OnClick="Button1_Click" />
                                </div>

                            </div>
                        </div>

                        

                    </div>
                </div>

                <br />
                <a class="btn btn-primary" href="home.aspx"><i class="fas fa-angle-left"></i> <i class="fa fa-home"></i></a>
                <br />
                <br />

            </div>
        </div>
    </div>

    <script type="text/javascript">
        function togglePasswordVisibility(textBoxId) {
            var passwordField = document.getElementById(textBoxId);
            var icon = passwordField.nextElementSibling.querySelector('i');

            if (passwordField.type === "password") {
                passwordField.type = "text";
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = "password";
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        }
    </script>

</asp:Content>