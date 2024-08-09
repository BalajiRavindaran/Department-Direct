<%@ Page Title="Faculty Chats" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="facultyChats.aspx.cs" Inherits="DepartmentDirect.facultyChats" %>
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
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .chat-item {
            background: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
            padding: 15px;
            width: 100%;
            max-width: 800px;
            cursor: pointer;
        }

        .chat-item:hover {
            background: #e0e0e0;
        }

        .chat-title {
            font-size: 18px;
            font-weight: bold;
        }

        .chat-summary {
            font-size: 14px;
        }

        .reply-input-container {
            margin-top: 20px;
            display: flex;
            width: 100%;
            max-width: 800px;
        }

        .reply-textbox {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .reply-button {
            padding: 10px;
            background-color: #912338;
            color: white;
            border: none;
            border-radius: 5px;
            margin-left: 10px;
        }

        .reply-button:hover {
            background-color: #800020;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="title">Active Chats</div>
    <div class="container">
        <asp:Repeater ID="chatRepeater" runat="server" OnItemCommand="chatRepeater_ItemCommand">
            <ItemTemplate>
                <div class="chat-item" CommandArgument='<%# Eval("Id") %>' CommandName="GoToConversation">
                    <div class="chat-title"><%# Eval("Title") %></div>
                    <div class="chat-summary"><%# Eval("Summary") %></div>
                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>

    <div class="reply-input-container">
        <asp:TextBox CssClass="reply-textbox" ID="ReplyTextBox" runat="server" placeholder="Type your reply..." TextMode="MultiLine" Rows="3"></asp:TextBox>
        <button class="reply-button" onclick="SendReply()">Send Reply</button>
    </div>
</asp:Content>
