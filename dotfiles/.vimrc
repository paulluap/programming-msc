"""vim-plug


" default script installs under .vim/autoload/
if filereadable(expand("~/.vim/vim-plug/plug.vim"))
    source ~/.vim/vim-plug/plug.vim
endif

call plug#begin('~/.vim/plugged')

Plug 'scrooloose/nerdtree'              " NerdTree is a tree view for vim.
Plug 'bling/vim-airline'                " A useful statusbar.

Plug 'altercation/vim-colors-solarized' " Solarized Colorscheme for Vim
Plug 'skywind3000/asyncrun.vim'         " run commands in background and read output in the quickfix (vim8)
Plug 'mattn/emmet-vim'                  " for xml editing, abbreviation expansion 
Plug 'tpope/vim-fugitive'               " Git support
Plug 'tpope/vim-surround'               " Surround motions
Plug 'preservim/nerdcommenter'          " NERD Commenter plugin.
Plug 'rking/ag.vim'                     " Front end of ag (requires silversearcher-ag)
Plug 'tpope/vim-repeat'                 " Allow the 'dot' for repeating even for plugins.
Plug 'dhruvasagar/vim-table-mode'
Plug 'jeetsukumaran/vim-buffergator'    " buffer navigator
Plug 'majutsushi/tagbar'                " code outline


" to try this 
" Plug 'neoclide/coc.nvim', {'branch': 'release'}   " Modern, LSP-based autocompletion.

" FZF Vim Integration.
Plug 'junegunn/fzf', { 'dir': '~/.fzf', 'do': './install --bin' }
Plug 'junegunn/fzf.vim'

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
set history=100         " keep 100 lines of history

set autoindent          " always set autoindenting on
set copyindent          " copy the previous indentation on autoindenting


set viminfo='20,\"80            " read/write a .viminfo file, don't store more
                                "    than 80 lines of registers

set visualbell                  " don't beep
set noerrorbells                " don't beep

set wildmenu                    " make tab completion for files/buffers act like bash
set wildignore=*.swp,*.bak,*.pyc,*.class

set re=0                " without this, open ts is slow ...


autocmd FileType yaml setlocal ts=2 sts=2 sw=2 expandtab
autocmd FileType typescript setl sw=2 sts=2 et
autocmd FileType javascript setl sw=2 sts=2 et
autocmd FileType xml setl sw=2 sts=2 et
autocmd FileType html setl sw=2 sts=2 et

"" basic shortcuts
nnoremap <c-h> :bprevious<cr> 
nnoremap <c-l> :bnext<cr>

" Plugin: NerdTree settings
    nnoremap <C-t> :NERDTreeToggle<CR>

  


"TODO refactor vim script like "https://github.com/dwmkerr/dotfiles/blob/master/vim/vimrc
