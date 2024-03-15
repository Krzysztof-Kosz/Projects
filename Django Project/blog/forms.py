from django.forms import ModelForm
from .models import Article, Comment, Post, PostComment

class ArticleForm(ModelForm):
    class Meta:
        model = Article
        fields = '__all__'
        exclude = ['author']

class CommentForm(ModelForm):
    class Meta:
        model = Comment
        fields = '__all__'
        exclude=['author', 'article']


class PostCommentForm(ModelForm):
    class Meta:
        model = PostComment
        fields = '__all__'
        exclude=['author', 'post']

class PostForm(ModelForm):
    class Meta:
        model = Post
        fields = '__all__'
        exclude = ['author']