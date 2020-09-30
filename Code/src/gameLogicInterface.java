public interface gameLogicInterface {
    public void getCoordinates();

    public void Loop(Board board1, Board board2, BoardPrinterWrapper UI);

    public void markBoard(Board opponent, BoardPrinterWrapper opboard, BoardPrinterWrapper playerboard);

    public int getRow();
    
    public int getCol();



}