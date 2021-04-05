"""vim-plug
source ~/.vim/vim-plug/plug.vim

call plug#begin('~/.vim/plugged')

Plug 'scrooloose/nerdtree'
Plug 'bling/vim-airline'
Plug 'junegunn/fzf', { 'dir': '~/.fzf', 'do': './install --bin' }
Plug 'junegunn/fzf.vim'
Plug 'altercation/vim-colors-solarized'
Plug 'skywind3000/asyncrun.vim'

call plug#end()


let g:solarized_termcolors=256
colorscheme solarized

set t_Co=256            " iTerm2 supports 256 color mode. 
set history=100         " keep 100 lines of history


syntax on               " syntax highlighting

set nobackup            " do not keep backup files, it's 70's style cluttering
set nowb
set noswapfile

set hlsearch
set incsearch
set ignorecase

set expandtab           " expand tabs to spaces by default
set shiftwidth=4        " number of spaces to use for autoindenting
set tabstop=4           " a tab is four space

set cursorline          " hightlight curent line
        
set t_Co=256            " iTerm2 supports 256 color mode. 
set ai                  " auto indenting
set history=100         " keep 100 lines of history

set autoindent          " always set autoindenting on
set copyindent          " copy the previous indentation on autoindenting


set viminfo='20,\"80            " read/write a .viminfo file, don't store more
                                "    than 80 lines of registers

set visualbell                  " don't beep
set noerrorbells                " don't beep

set wildmenu                    " make tab completion for files/buffers act like bash
set wildignore=*.swp,*.bak,*.pyc,*.class

autocmd FileType yaml setlocal ts=2 sts=2 sw=2 expandtab
autocmd FileType typescript setl sw=2 sts=2 et
autocmd FileType javascript setl sw=2 sts=2 et
autocmd FileType xml setl sw=2 sts=2 et


"TODO refactor vim script like "https://github.com/dwmkerr/dotfiles/blob/master/vim/vimrc
