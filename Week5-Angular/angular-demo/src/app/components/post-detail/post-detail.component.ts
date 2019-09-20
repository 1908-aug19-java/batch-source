import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/models/Post';
import { PostService } from 'src/app/services/post.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {

  currentPost: Post = new Post();

  constructor(private postService: PostService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(param=>{
      this.currentPost.id = param['id'];
      console.log(this.currentPost.id);
      // get post by current ID from our post service 
      this.getPost(this.currentPost.id);
    })
  }

  getPost(idParam: number){
    this.postService.getPostById(idParam)
      .then((response)=>{
        this.currentPost = response;
        console.log(this.currentPost);
      })
      .catch((e)=>{
        console.log(e);
      })
  }

}
