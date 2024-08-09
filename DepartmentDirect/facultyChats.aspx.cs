using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Web.UI.WebControls;

namespace DepartmentDirect
{
    public partial class facultyChats : System.Web.UI.Page
    {
        private readonly string apiUrl = "http://3.128.202.148:9090/departmentdirect/qa/create";
        private readonly string chatListApiUrl = "http://3.128.202.148:9090/departmentdirect/qa/list"; // Replace with actual API for fetching chats

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                LoadActiveChats();
            }
        }

        private void LoadActiveChats()
        {
            // Sample data, replace with actual API call to get active chats
            var chats = new List<Chat>
            {
                new Chat { Id = 1, Title = "Electrical Engineering Queries", Summary = "Discussion on various topics related to Electrical Engineering." },
                new Chat { Id = 2, Title = "Computer Engineering Discussions", Summary = "Questions and answers on Computer Engineering topics." },
                new Chat { Id = 3, Title = "Co-Op Education Insights", Summary = "Information and questions about Co-Op Education opportunities." }
            };

            chatRepeater.DataSource = chats;
            chatRepeater.DataBind();
        }

        protected void chatRepeater_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "GoToConversation")
            {
                string chatId = e.CommandArgument.ToString();
                // Redirect to the chat page with chatId and departmentId
                Response.Redirect($"chatFaculty.aspx?chatId={chatId}");
            }
        }

        protected void SendReply_Click(object sender, EventArgs e)
        {
            string replyMessage = ReplyTextBox.Text;
            if (string.IsNullOrEmpty(replyMessage))
            {
                // Handle empty message
                return;
            }

            string studentId = Session["StudentId"]?.ToString();
            int departmentId = 1; // Replace with actual department ID if needed

            if (string.IsNullOrEmpty(studentId))
            {
                // Handle missing student ID
                return;
            }

            PostReply(replyMessage, studentId, departmentId).Wait();
        }

        private async Task PostReply(string message, string studentId, int departmentId)
        {
            using (HttpClient client = new HttpClient())
            {
                try
                {
                    var formData = new MultipartFormDataContent();
                    formData.Add(new StringContent(studentId), "StudentID");
                    formData.Add(new StringContent(departmentId.ToString()), "DepartmentID");
                    formData.Add(new StringContent(message), "Message");
                    formData.Add(new StringContent("faculty"), "Role");

                    HttpResponseMessage response = await client.PostAsync(apiUrl, formData);
                    if (response.IsSuccessStatusCode)
                    {
                        // Handle successful response
                        ReplyTextBox.Text = ""; // Clear the textbox
                        LoadActiveChats(); // Optionally reload chats
                    }
                    else
                    {
                        // Handle failure
                        // Show error message on the UI
                    }
                }
                catch (Exception ex)
                {
                    // Handle exception
                    // Show error message on the UI
                }
            }
        }

        public class Chat
        {
            public int Id { get; set; }
            public string Title { get; set; }
            public string Summary { get; set; }
        }
    }
}
