<%@ Page Title="Chat with Student" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="chatStudent.aspx.cs" Inherits="DepartmentDirect.chatStudent" %>
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

        .title {
            color: #912338;
            font-size: 50px;  
            text-align: center;
            padding: 20px 0;
        }

        .container {
            display: flex;
            height: 80vh;
            justify-content: center;
        }

        .chat-section {
            width: 100%;
            max-width: 800px;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .chat-box {
            flex: 1;
            border: 1px solid #ccc;
            padding: 10px;
            overflow-y: scroll;
            background: #f9f9f9;
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column-reverse; /* Ensure newest messages are at the bottom */
            color: black
        }

        .message {
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 5px;
            width: 100%; /* Full width */
            box-sizing: border-box; /* Include padding in width calculation */
            word-wrap: break-word;
        }

        .message.Student {
            text-align: right;
            background: black;
            color: white;
        }

        .message.Staff {
            text-align: left;
            background: #d1e0e0;
        }

        .input-container {
            margin-top: 10px;
            display: flex;
            width: 100%;
            max-width: 800px;
            gap: 10px; /* Add spacing between the elements */
        }

        input[type="text"], .form-control {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .send-button {
            padding: 10px;
            background-color: #800020;
            color: white;
            border: none;
            border-radius: 5px;
            margin-left: 10px;
        }

        .send-button {
            background-color: #800020;
        }

        .Literal1 {
            text-align: center;
            color: red;
            font-size: 20px;
        }
    </style>

    <div class="title">Chat with Faculty</div>

    <div class="container">
        <div class="chat-section">
            <div class="chat-box" id="chat-box">
                <asp:Literal ID="ChatContent" runat="server"></asp:Literal>
            </div>
            <div class="input-container">
                <asp:TextBox CssClass="form-control" ID="TextBox1" runat="server" placeholder="Type a message..." />
                <asp:DropDownList CssClass="form-control" ID="DropDownList2" runat="server">
                    <asp:ListItem Text="Select" Value="Select"/>
                    <asp:ListItem Text="Department inquiry" Value="Department inquiry"/>
                    <asp:ListItem Text="Course Pre-Requsite" Value="Course Pre-Requsite"/>
                    <asp:ListItem Text="Course Similarities/Differences" Value="Course Similarities/Differences"/>
                    <asp:ListItem Text="Others" Value="Others"/>
                </asp:DropDownList>
                <asp:Button ID="Button1" runat="server" CssClass="send-button" Text="Send" OnClick="SendMessage_Click"/>
            </div>
        </div>
    </div>

    <div class="Literal1"><asp:Literal ID="Literal1" runat="server" ></asp:Literal></div>

    <br />
    <div style="text-align:center;">
        <a class="btn btn-primary" href="home.aspx"><i class="fas fa-angle-left"></i> <i class="fa fa-home"></i></a>
    </div>
    <br />

</asp:Content>

