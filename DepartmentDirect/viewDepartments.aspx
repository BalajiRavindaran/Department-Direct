<%@ Page Title="" Language="C#" MasterPageFile="~/DepartmentDirect.Master" AutoEventWireup="true" CodeBehind="viewDepartments.aspx.cs" Inherits="DepartmentDirect.viewDepartments" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
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
            font-size: 20px;
            color: black;
        }

        .title {
            padding: 20px 0 0 0;
        }

        .title {
            color: #912338;
            font-size: 50px;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <br />
    <div class="container">
        <h1 class="title">Departments</h1>
    </div>
    <div class="container mt-5">
        <div class="row">
            <asp:Repeater ID="departmentRepeater" runat="server" OnItemCommand="departmentRepeater_ItemCommand">
                <ItemTemplate>
                    <div class="col-md-4 mb-4 d-flex align-items-stretch">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title title"><%# Eval("Name") %></h5>
                                <h5 class="card-text">Previous Conversations: <%# Eval("PreviousConversations") %></h5>
                                <asp:LinkButton runat="server" CssClass="btn btn-primary" CommandArgument='<%# Eval("Id") %>' CommandName="GoToChat">Go to Chat</asp:LinkButton>
                            </div>
                        </div>
                    </div>
                </ItemTemplate>
            </asp:Repeater>
        </div>
    </div>
</asp:Content>
