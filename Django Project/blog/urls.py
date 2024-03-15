from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name="blog-home"),
    path('about/', views.about, name="blog-about"),
    path('forum/', views.forum, name="forum"),
    path('testing/', views.testing, name='testing'),
    path('article/<int:id>', views.article, name='article'),
    path('login/', views.loginPage, name='login'),
    path('logout/', views.logoutUser, name='logout'),
    path('register/', views.registerUser, name='register'),
    path('article_form/', views.articleForm, name='article_form'),
    path('update_article/<str:id>', views.updateArticle, name='update_article'),
    path('delete_article/<str:id>', views.deleteArticle, name='delete_article'),
    path('forum/post/<int:id>', views.post, name='post'),
    path('forum/post_form/', views.postForm, name='post_form'),
    path('forum/update_post/<str:id>', views.updatePost, name='update_post'),
    path('forum/delete_post/<str:id>', views.deletePost, name='delete_post'),
]
