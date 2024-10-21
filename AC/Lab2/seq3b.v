module seq3b (
  input [3:0] i,
  output reg o
);
  
always @(*) begin
    case(i)
      0, 1, 7, 8, 14, 15: o=1;
      default: o=0;
    endcase
end
endmodule

module seq3b_tb;
  reg [3:0] i;
  wire o;

  seq3b seq3b_i (.i(i), .o(o));

  integer k;
  initial begin
    $display("Time\ti\t\to");
    $monitor("%0t\t%b(%2d)\t%b", $time, i, i, o);
    i = 0;
    for (k = 1; k < 16; k = k + 1)
      #10 i = k;
  end
endmodule