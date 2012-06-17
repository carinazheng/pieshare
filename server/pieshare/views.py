from django.shortcuts import render_to_response

def user_profile(request):
    return render_to_response('pieshare/user_profile.html',
             {'user': request.user,
              'request': request,})

