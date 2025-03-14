#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoord;

uniform sampler2D u_texture;
uniform vec2 u_resolution;
uniform sampler2D u_buffer;

// inspired by Camouflag on shadertoy

void main(){
    vec2 uv = v_texCoord;
    vec2 bgMask = texture2D(u_buffer, uv).xy - texture2D(u_buffer, u_resolution).xy;
    // sample the texture based on coord subtracted by scaled buffer's uv
    vec4 color = texture2D(u_texture, uv - bgMask * 0.015);
    
    gl_FragColor = color;
}