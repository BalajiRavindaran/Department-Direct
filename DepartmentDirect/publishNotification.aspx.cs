using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Web.UI;

namespace DepartmentDirect
{
    public partial class publishNotification : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Any initialization code goes here
            }
        }

        protected void ButtonSubmit_Click(object sender, EventArgs e)
        {
            Label1.Visible = true;
            Label1.ForeColor = System.Drawing.Color.Black;
            Label1.Text = "Publishing notification...";

            string notificationText = TextBoxNotification.Text;
            string id = Session["AdminId"] as string;
            string selectedCategory = DropDownListCategories.SelectedValue;

            if (string.IsNullOrEmpty(notificationText) || selectedCategory == "Select")
            {
                // Handle validation errors
                // Show error message or use a validation control
                return;
            }
            

            // Create HttpClient and send POST request
            using (HttpClient client = new HttpClient())
            {
                try
                {
                    var formData = new MultipartFormDataContent();
                    formData.Add(new StringContent(id), "AdminID");
                    formData.Add(new StringContent(notificationText), "Message");
                    formData.Add(new StringContent(selectedCategory), "Types");

                    // Use .Result to make it synchronous
                    HttpResponseMessage response = client.PostAsync("http://3.128.202.148:9090/departmentdirect/nt/send", formData).Result;

                    response.EnsureSuccessStatusCode();

                    // Handle success
                    //Label1.Text = "Notification published successfully!";
                }
                catch (Exception ex)
                {
                    // Handle any exceptions
                    // Example: Label1.Text = $"Error: {ex.Message}";
                }
                finally
                {
                    // Hide loading indicator
                    Label1.ForeColor = System.Drawing.Color.Green;
                    Label1.Text = "Notification was successfully published";
                }
            }
        }
    }
}
