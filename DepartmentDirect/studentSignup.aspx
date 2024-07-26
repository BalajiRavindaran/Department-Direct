<%@ Page Title="signup" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="studentSignup.aspx.cs" Inherits="DepartmentDirect.studentSignup" Async="true" %>
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

        h3, h4, h5, h6 {
            font-size: 2.1vw;
            color: red;
        }

        .pill {
            font-size: 25px;
        }

        label, span, li {
            color: #000000;
        }

        .blink {
            font-size: 20px;
            animation: blinker 1.5s linear infinite;
        }

        @keyframes blinker {
            50% {
                opacity: 0;
            }
        }

        .title {
            color: #912338;
            font-size: 50px;  
        }

        .signup {
            background: #912338;
            color: white;
        }

        .signup:hover {
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
                                    <img src="assets/signup.png" width="100px"/>
                                </center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <center>
                                    <h1 class="title">Student Sign Up</h1>
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
                                <center><p class="pill"><span class="badge bg-dark" style="color:white">Personal Credentials</span></p></center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label>Full Name</label>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Full Name"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Date Of Birth</label>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox2" runat="server" placeholder="dd-mm-yyyy" TextMode="Date"></asp:TextBox>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label>Email</label>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox3" runat="server" placeholder="Email" TextMode="Email"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Notifications</label>
                                <div class="form-group">
                                    <asp:DropDownList CssClass="form-control" ID="DropDownList2" runat="server">
                                        <asp:ListItem Text="Select" Value="Select"/>
                                        <asp:ListItem Text="Co-Op Updates" Value="Co-Op Updates"/>
                                        <asp:ListItem Text="Gina-Cody News" Value="Gina-Cody News"/>
                                        <asp:ListItem Text="Events" Value="Events"/>
                                        <asp:ListItem Text="All" Value="All"/>
                                    </asp:DropDownList>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <hr />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col">
                               <center><p class="pill"><span class="badge bg-dark" style="color:white">Login Credentials</span></p></center>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <label>Student ID</label>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox8" runat="server" placeholder="Student ID"></asp:TextBox>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <label>Password</label>
                                <div class="form-group">
                                    <asp:TextBox CssClass="form-control" ID="TextBox9" runat="server" placeholder="Password" TextMode="Password"></asp:TextBox>
                                </div>
                            </div>
                        </div>

                        <ul CssClass="form-control">
                            <li>Password must be 8 characters long</li>
                            <li>Password should contain atleast 1 special characters</li>
                            <li>Password should contain atleast 1 number</li>
                        </ul>

                        <center>
                            <asp:Label ID="Label1" CssClass="blink" runat="server" Text="Label" Visible="False"></asp:Label>
                        </center>
                        <br />

                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <asp:Button CssClass="btn btn-block signup" ID="Button1" runat="server" Text="Sign Up" OnClick="Button1_Click"/>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                
                <br />
                <a class="btn btn-primary" href="home.aspx"><i class="fas fa-angle-left"></i> <i class="fa fa-home"></i></a>
                <br /><br />

            </div>
        </div>
    </div>
</asp:Content>
