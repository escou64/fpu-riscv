module Comparator(
  input  [3:0] io_i_scr1,
  input  [3:0] io_i_scr2,
  input        io_i_inf,
  input        io_i_eq,
  input        io_i_sup,
  output       io_o_inf,
  output       io_o_eq,
  output       io_o_sup
);
  wire  _T_1 = ~io_i_inf; // @[comparator.scala 19:40]
  wire  _T_2 = io_i_eq & _T_1; // @[comparator.scala 19:28]
  wire  _T_3 = ~io_i_sup; // @[comparator.scala 19:61]
  wire  _T_4 = _T_2 & _T_3; // @[comparator.scala 19:49]
  wire  _T_5 = io_i_scr1 > io_i_scr2; // @[comparator.scala 20:24]
  wire  _T_6 = io_i_scr1 < io_i_scr2; // @[comparator.scala 24:31]
  wire  _GEN_1 = _T_6 ? 1'h0 : 1'h1; // @[comparator.scala 24:44]
  wire  _GEN_4 = _T_5 ? 1'h0 : _GEN_1; // @[comparator.scala 20:36]
  wire  _GEN_5 = _T_5 ? 1'h0 : _T_6; // @[comparator.scala 20:36]
  wire  _T_7 = ~io_i_eq; // @[comparator.scala 33:26]
  wire  _T_9 = _T_7 & io_i_inf; // @[comparator.scala 33:35]
  wire  _T_11 = _T_9 & _T_3; // @[comparator.scala 33:56]
  wire  _T_14 = _T_7 & _T_1; // @[comparator.scala 37:35]
  wire  _T_16 = _T_14 & io_i_sup; // @[comparator.scala 37:56]
  wire  _GEN_8 = _T_11 ? 1'h0 : _T_16; // @[comparator.scala 33:77]
  assign io_o_inf = _T_4 ? _GEN_5 : _T_11; // @[comparator.scala 23:22 comparator.scala 27:22 comparator.scala 31:22 comparator.scala 36:18 comparator.scala 40:18 comparator.scala 44:18]
  assign io_o_eq = _T_4 & _GEN_4; // @[comparator.scala 22:21 comparator.scala 26:21 comparator.scala 30:21 comparator.scala 35:17 comparator.scala 39:17 comparator.scala 43:17]
  assign io_o_sup = _T_4 ? _T_5 : _GEN_8; // @[comparator.scala 21:22 comparator.scala 25:22 comparator.scala 29:22 comparator.scala 34:18 comparator.scala 38:18 comparator.scala 42:18]
endmodule
module Alu(
  input        clock,
  input        reset,
  input  [2:0] io_i_op,
  input  [3:0] io_i_scr1,
  input  [3:0] io_i_scr2,
  input        io_i_inf,
  input        io_i_eq,
  input        io_i_sup,
  output [3:0] io_o_res,
  output       io_o_N,
  output       io_o_inf,
  output       io_o_eq,
  output       io_o_sup
);
  wire [3:0] Comparateur_io_i_scr1; // @[alu.scala 25:29]
  wire [3:0] Comparateur_io_i_scr2; // @[alu.scala 25:29]
  wire  Comparateur_io_i_inf; // @[alu.scala 25:29]
  wire  Comparateur_io_i_eq; // @[alu.scala 25:29]
  wire  Comparateur_io_i_sup; // @[alu.scala 25:29]
  wire  Comparateur_io_o_inf; // @[alu.scala 25:29]
  wire  Comparateur_io_o_eq; // @[alu.scala 25:29]
  wire  Comparateur_io_o_sup; // @[alu.scala 25:29]
  wire  _T = 3'h0 == io_i_op; // @[Conditional.scala 37:30]
  wire [3:0] _T_2 = io_i_scr1 + io_i_scr2; // @[alu.scala 46:35]
  wire  _T_3 = 3'h1 == io_i_op; // @[Conditional.scala 37:30]
  wire  _T_4 = io_i_scr1 > io_i_scr2; // @[alu.scala 50:28]
  wire [3:0] _T_6 = io_i_scr1 - io_i_scr2; // @[alu.scala 51:39]
  wire [3:0] _T_8 = io_i_scr2 - io_i_scr1; // @[alu.scala 54:39]
  wire [3:0] _GEN_0 = _T_4 ? _T_6 : _T_8; // @[alu.scala 50:40]
  wire  _GEN_1 = _T_4 ? 1'h0 : 1'h1; // @[alu.scala 50:40]
  wire  _T_9 = 3'h4 == io_i_op; // @[Conditional.scala 37:30]
  wire  _T_10 = Comparateur_io_i_inf; // @[alu.scala 59:39]
  wire  _T_11 = ~Comparateur_io_i_eq; // @[alu.scala 59:71]
  wire  _T_12 = _T_10 & _T_11; // @[alu.scala 59:48]
  wire  _T_13 = ~Comparateur_io_i_sup; // @[alu.scala 59:104]
  wire  _T_14 = _T_12 & _T_13; // @[alu.scala 59:80]
  wire  _T_15 = ~Comparateur_io_i_inf; // @[alu.scala 61:46]
  wire  _T_17 = _T_15 & _T_11; // @[alu.scala 61:55]
  wire  _T_18 = Comparateur_io_i_sup; // @[alu.scala 61:111]
  wire  _T_19 = _T_17 & _T_18; // @[alu.scala 61:87]
  wire  _T_21 = Comparateur_io_i_eq; // @[alu.scala 63:78]
  wire  _T_22 = _T_15 & _T_21; // @[alu.scala 63:55]
  wire  _T_24 = _T_22 & _T_13; // @[alu.scala 63:87]
  wire [3:0] _GEN_2 = _T_24 ? io_i_scr2 : 4'h0; // @[alu.scala 63:120]
  wire [3:0] _GEN_3 = _T_19 ? io_i_scr2 : _GEN_2; // @[alu.scala 61:120]
  wire [3:0] _GEN_4 = _T_14 ? io_i_scr1 : _GEN_3; // @[alu.scala 59:113]
  wire  _T_25 = 3'h5 == io_i_op; // @[Conditional.scala 37:30]
  wire [3:0] _GEN_6 = _T_14 ? io_i_scr2 : _GEN_2; // @[alu.scala 71:120]
  wire [3:0] _GEN_7 = _T_19 ? io_i_scr1 : _GEN_6; // @[alu.scala 69:114]
  wire [3:0] _GEN_8 = _T_25 ? _GEN_7 : 4'h0; // @[Conditional.scala 39:67]
  wire [3:0] _GEN_10 = _T_9 ? _GEN_4 : _GEN_8; // @[Conditional.scala 39:67]
  wire [3:0] _GEN_12 = _T_3 ? _GEN_0 : _GEN_10; // @[Conditional.scala 39:67]
  wire  _GEN_13 = _T_3 & _GEN_1; // @[Conditional.scala 39:67]
  Comparator Comparateur ( // @[alu.scala 25:29]
    .io_i_scr1(Comparateur_io_i_scr1),
    .io_i_scr2(Comparateur_io_i_scr2),
    .io_i_inf(Comparateur_io_i_inf),
    .io_i_eq(Comparateur_io_i_eq),
    .io_i_sup(Comparateur_io_i_sup),
    .io_o_inf(Comparateur_io_o_inf),
    .io_o_eq(Comparateur_io_o_eq),
    .io_o_sup(Comparateur_io_o_sup)
  );
  assign io_o_res = _T ? _T_2 : _GEN_12; // @[alu.scala 81:14]
  assign io_o_N = _T ? 1'h0 : _GEN_13; // @[alu.scala 85:12]
  assign io_o_inf = Comparateur_io_o_inf; // @[alu.scala 82:14]
  assign io_o_eq = Comparateur_io_o_eq; // @[alu.scala 83:13]
  assign io_o_sup = Comparateur_io_o_sup; // @[alu.scala 84:14]
  assign Comparateur_io_i_scr1 = io_i_scr1; // @[alu.scala 28:27]
  assign Comparateur_io_i_scr2 = io_i_scr2; // @[alu.scala 29:27]
  assign Comparateur_io_i_inf = io_i_inf; // @[alu.scala 30:26]
  assign Comparateur_io_i_eq = io_i_eq; // @[alu.scala 31:25]
  assign Comparateur_io_i_sup = io_i_sup; // @[alu.scala 32:26]
endmodule
