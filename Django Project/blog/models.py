from django.db import models
from django.contrib.auth.models import User


class Article(models.Model):
    TOPIC_CHOICES = [
        ('gastronomy', 'Gastronomia'),
        ('locals', 'Noclegi'),
        ('travel', 'Podróże'),
        ('footRoute', 'Trasy piesze'),
        ('bikeRoute', 'Trasy rowerowe'),
        ('carRoute', 'Trasy samochodwe')
    ]

    author = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='Autor')
    title = models.CharField(max_length=255, verbose_name='Tytuł')
    topic = models.CharField(max_length=20, choices=TOPIC_CHOICES, verbose_name='Temat')
    summary = models.TextField(verbose_name='Skrót')
    content = models.TextField(verbose_name='Tekst')
    created_at = models.DateTimeField(auto_now_add=True, verbose_name='Data utworzenia')
    updated_at = models.DateTimeField(auto_now=True, verbose_name='Data ostatniej aktualizacji')

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return self.title

class Comment(models.Model):
    author = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='Autor')
    title = models.CharField(max_length=255, verbose_name='Tytuł')
    content = models.TextField(verbose_name='Tekst')
    article = models.ForeignKey(Article, on_delete=models.CASCADE, verbose_name='Artykuł')
    created_at = models.DateTimeField(auto_now_add=True, verbose_name='Data utworzenia')
    updated_at = models.DateTimeField(auto_now=True, verbose_name='Data ostatniej aktualizacji')

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return self.title

class Post(models.Model):
    TOPIC_CHOICES = [
        ('gastronomy', 'Gastronomia'),
        ('locals', 'Noclegi'),
        ('travel', 'Podróże'),
        ('footRoute', 'Trasy piesze'),
        ('bikeRoute', 'Trasy rowerowe'),
        ('carRoute', 'Trasy samochodwe')
    ]

    author = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='Autor')
    title = models.CharField(max_length=255, verbose_name='Tytuł')
    topic = models.CharField(max_length=20, choices=TOPIC_CHOICES, verbose_name='Temat')
    summary = models.TextField(verbose_name='Skrót')
    content = models.TextField(verbose_name='Tekst')
    created_at = models.DateTimeField(auto_now_add=True, verbose_name='Data utworzenia')
    updated_at = models.DateTimeField(auto_now=True, verbose_name='Data ostatniej aktualizacji')

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return self.title

class PostComment(models.Model):
    author = models.ForeignKey(User, on_delete=models.SET_NULL, null=True, verbose_name='Autor')
    title = models.CharField(max_length=255, verbose_name='Tytuł')
    content = models.TextField(verbose_name='Tekst')
    post = models.ForeignKey(Post, on_delete=models.CASCADE, verbose_name='Post')
    created_at = models.DateTimeField(auto_now_add=True, verbose_name='Data utworzenia')
    updated_at = models.DateTimeField(auto_now=True, verbose_name='Data ostatniej aktualizacji')

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return self.title