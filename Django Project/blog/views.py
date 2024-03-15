from django.shortcuts import render, redirect
from django.contrib import messages
from .models import *
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.views import LoginView, LogoutView
from django.contrib.auth.forms import AuthenticationForm
from .forms import *
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.http import HttpResponse

def loginPage(request):
    if request.user.is_authenticated:
        return redirect('http://127.0.0.1:8000')

    if request.method == 'POST':
        username = request.POST.get('username')
        password = request.POST.get('password')

        try:
            user = User.objects.get(username=username)
        except:
            messages.error(request, 'User does not exist')

        user = authenticate(request, username=username, password=password)

        if user is not None:
            login(request, user)
            return redirect('http://127.0.0.1:8000')
        else:
            messages.error(request, 'Username OR password does not exit')

    return render(request, 'blog/login.html')

def logoutUser(request):
    logout(request)
    return redirect('http://127.0.0.1:8000')

def registerUser(request):
    form = UserCreationForm()

    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            user = form.save(commit=False)
            user.save()
            login(request, user)
            return redirect('http://127.0.0.1:8000')
        else:
            messages.error(request, 'An error occurred during registration')

    return render(request, 'blog/register.html', {'form': form})

def home(request):
    articles = Article.objects.all()
    context = {
        'articles': articles,
    }
    return render(request, 'blog/home.html', context)

def about(request):
    return render(request, 'blog/about.html')

def forum(request):
    posts = Post.objects.all()
    context = {
        'posts': posts,
    }
    return render(request, 'forum/forum.html', context)

def testing(request):
    return render(request, 'blog/testing.html')

def article(request, id):
    article = Article.objects.get(id=id)
    comments = Comment.objects.filter(article_id=article.id)

    form = CommentForm()

    if request.method == 'POST':
        form = CommentForm(request.POST)

        if form.is_valid():
            Comment.objects.create(
                author_id=request.user.id,
                title=request.POST.get('title'),
                content=request.POST.get('content'),
                article_id=id
            )

            return redirect(f'http://127.0.0.1:8000/article/{id}')

    context = {
        'article': article,
        'comments': comments,
        'form': form
    }
    return render(request, 'blog/article.html', context)

#@login_required(login_url='login')
def articleForm(request):
    form = ArticleForm()

    if request.user not in User.objects.all().filter(is_staff=True):
        return HttpResponse('You are not allowed here!!')

    if request.method == 'POST':
        form = ArticleForm(request.POST)
        if form.is_valid():

            Article.objects.create(
                author_id=request.user.id,
                title=request.POST.get('title'),
                topic=request.POST.get('topic'),
                summary=request.POST.get('summary'),
                content=request.POST.get('content')
            )
            return redirect('http://127.0.0.1:8000')

    context = {'form': form}
    return render(request, 'blog/article_form.html', context)


#@login_required(login_url='login')
def updateArticle(request, id):
    article = Article.objects.get(id=id)
    form = ArticleForm(instance=article)

    if request.user != article.author:
        return HttpResponse('You are not allowed here!!')

    if request.method == 'POST':
        form = ArticleForm(request.POST, instance=article)
        if form.is_valid():
            form.save()
            return redirect('http://127.0.0.1:8000')

    context = {'form': form}
    return render(request, 'blog/article_form.html', context)


#@login_required(login_url='login')
def deleteArticle(request, id):
    article = Article.objects.get(id=id)

    if request.user != article.author:
        return HttpResponse('You are not allowed here!!')

    if request.method == 'POST':
        article.delete()
        return redirect('http://127.0.0.1:8000')
    return render(request, 'blog/delete.html', {'obj': article})

def post(request, id):
    post = Post.objects.get(id=id)
    comments = PostComment.objects.filter(post_id=post.id)

    form = PostCommentForm()

    if request.method == 'POST':
        form = PostCommentForm(request.POST)

        if form.is_valid():
            PostComment.objects.create(
                author_id=request.user.id,
                title=request.POST.get('title'),
                content=request.POST.get('content'),
                post_id=id
            )

            return redirect(f'http://127.0.0.1:8000/forum/post/{id}')
    context = {
        'post': post,
        'comments': comments,
        'form': form
    }
    return render(request, 'forum/post.html', context)

@login_required(login_url='login')
def postForm(request):
    form = PostForm()

    if request.method == 'POST':
        form = PostForm(request.POST)
        if form.is_valid():

            Post.objects.create(
                author_id=request.user.id,
                title=request.POST.get('title'),
                topic=request.POST.get('topic'),
                summary=request.POST.get('summary'),
                content=request.POST.get('content')
            )
            return redirect('http://127.0.0.1:8000/forum')

    context = {'form': form}
    return render(request, 'forum/post_form.html', context)


@login_required(login_url='login')
def updatePost(request, id):
    post = Post.objects.get(id=id)
    form = PostForm(instance=post)

    if request.user != post.author:
        return HttpResponse('You are not allowed here!!')

    if request.method == 'POST':
        form = PostForm(request.POST, instance=post)
        if form.is_valid():
            form.save()
            return redirect('http://127.0.0.1:8000/forum')

    context = {'form': form}
    return render(request, 'forum/post_form.html', context)


@login_required(login_url='login')
def deletePost(request, id):
    post = Post.objects.get(id=id)

    if request.user != post.author:
        return HttpResponse('Your are not allowed here!!')

    if request.method == 'POST':
        post.delete()
        return redirect('http://127.0.0.1:8000')
    return render(request, 'forum/delete.html', {'obj': post})