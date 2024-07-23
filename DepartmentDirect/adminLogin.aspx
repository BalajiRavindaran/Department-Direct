<%@ Page Title="" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="adminLogin.aspx.cs" Inherits="DepartmentDirect.adminLogin" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
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
                                    <img src="assets/admin.png" width="150px" />
                                </center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <center>
                                    <h1 class="title">Admin Login</h1>
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
                                    <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Admin ID"></asp:TextBox>
                                </div>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox2" runat="server" placeholder="Password" TextMode="Password"></asp:TextBox>
                                </div>
                                <br />
                                <center>
                                    <asp:Label ID="Label1" CssClass="blink" runat="server" Text="This is a text" Visible="False"></asp:Label></center>
                                <br />
                                <div class="form-group">
                                    <asp:Button CssClass="btn btn-block login" ID="Button1" runat="server" Text="Login"/>
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

</asp:Content>
