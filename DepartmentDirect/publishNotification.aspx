<%@ Page Title="Publish Notification" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="publishNotification.aspx.cs" Inherits="DepartmentDirect.publishNotification" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            display: grid;
            color: #ffffff;
        }

        .title {
            color: #912338;
            font-size: 50px;
            text-align: center;
            padding: 20px 0;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 80vh;
        }

        .form-container {
            width: 100%;
            max-width: 800px;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .textarea {
            width: 100%;
            height: 300px;
            margin-bottom: 20px;
        }

        .dropdown-container {
            display: flex;
            justify-content: space-between;
        }

        .dropdown-container select {
            width: 80%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .dropdown-container button {
            width: 18%;
            padding: 10px;
            background-color: #912338;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .dropdown-container button:hover {
            background-color: #800020;
        }

        .signup {
            background: #912338;
            color: white;
        }

        .signup:hover {
            color: yellow;
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
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="title">Publish Notification</div>
    <div class="container">
        <div class="form-container">
            <asp:TextBox ID="TextBoxNotification" runat="server" TextMode="MultiLine" CssClass="textarea" />
            <div class="dropdown-container">
                <asp:DropDownList ID="DropDownListCategories" runat="server">
                    <asp:ListItem Text="Select" Value="Select"/>
                    <asp:ListItem Text="Co-Op Updates" Value="Co-Op Updates"/>
                    <asp:ListItem Text="Gina-Cody News" Value="Gina-Cody News"/>
                    <asp:ListItem Text="Events" Value="Events"/>
                    <asp:ListItem Text="All" Value="All"/>
                </asp:DropDownList>
                <asp:Button ID="ButtonSubmit" CssClass="btn signup" runat="server" Text="Submit" OnClick="ButtonSubmit_Click" />
            </div>
            <center>
                <asp:Label ID="Label1" CssClass="blink" runat="server" Text="Label" Visible="False"></asp:Label>
            </center>
        </div>
    </div>
</asp:Content>